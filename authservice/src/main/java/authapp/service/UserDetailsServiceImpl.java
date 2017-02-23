package authapp.service;


import authapp.entity.UserEntity;
import authapp.repository.UserRepository;
import authapp.security.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * This service will request an user repository for information about a certain user
 *
 * @author WolfAlexander nikal@kth.se
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
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
        else
            return new JwtUserDetails(userEntity);
    }
}
