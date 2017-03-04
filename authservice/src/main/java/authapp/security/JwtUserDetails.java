package authapp.security;

import authapp.deserializer.JwtUserDetailsDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This object represents detail information about a user that is needed for authentication
 * and token generation
 */
@ToString
@JsonDeserialize(using = JwtUserDetailsDeserializer.class)
public class JwtUserDetails implements UserDetails {
    private  Long id;
    private  String username;
    private  String password;
    private  Collection<? extends GrantedAuthority> authorities;

    /**
     * Create user details with list of authorities
     * @param id - user id
     * @param username - name of the user
     * @param password - password to authenticate the user
     * @param authorities - granted authorities to this user
     */
    public JwtUserDetails(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    /**
     * Create user details with only one authority
     * @param id - user id
     * @param username - name of the user
     * @param password - password to authenticate the user
     * @param authority - granted authority to this user
     */
    public JwtUserDetails(Long id, String username, String password, GrantedAuthority authority) {
        this.id = id;
        this.username = username;
        this.password = password;

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(authority);
        this.authorities = grantedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }
}
