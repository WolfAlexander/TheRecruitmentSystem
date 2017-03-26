package authapp.service;

import authapp.entity.RoleEntity;
import authapp.entity.UserEntity;
import authapp.profile.ForTesting;
import authapp.repository.UserRepository;
import authapp.security.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This service will request an user repository for information about a certain user
 *
 * @author WolfAlexander nikal@kth.se
 */
@ForTesting
@Service
public class UserDetailsServiceTestImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves user information by username
     * @param username - user information will be identified by username
     * @return detailed information about the user
     * @throws UsernameNotFoundException if user with given name not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);

        if(userEntity == null)
            throw new UsernameNotFoundException("No user found with given username: " + username);
        else{
            List<GrantedAuthority> grantedAuthorities = this.mapToGrantedAuthorities(userEntity.getRoles());
            return new JwtUserDetails(userEntity.getId(), userEntity.getUsername(), userEntity.getPassword(), grantedAuthorities);
        }
    }

    private List<GrantedAuthority> mapToGrantedAuthorities(List<RoleEntity> roles){
        return roles.stream()
                .map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList());
    }
}