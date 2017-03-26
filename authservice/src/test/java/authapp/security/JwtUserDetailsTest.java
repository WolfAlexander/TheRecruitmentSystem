package authapp.security;

import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Testing to create valid JwtUserDetails
 * Scenario:
 * 1. Creating with list of authorities
 * 2. Creating with one authority
 */
public class JwtUserDetailsTest {

    @Test
    public void createWithListOfAuthorities(){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("TESTER"));
        authorities.add(new SimpleGrantedAuthority("SUPER_TESTER"));

        JwtUserDetails userDetails = new JwtUserDetails(0L, "username", "password", authorities);

        assertNotNull(userDetails);
        assertEquals("username", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());

        assertTrue(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("TESTER")));
        assertTrue(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("SUPER_TESTER")));

        assertTrue(userDetails.isAccountNonExpired());
        assertTrue(userDetails.isAccountNonLocked());
        assertTrue(userDetails.isCredentialsNonExpired());
        assertTrue(userDetails.isEnabled());
    }

    @Test
    public void createWithOneAuthority(){
        JwtUserDetails userDetails = new JwtUserDetails(0L, "username", "password", new SimpleGrantedAuthority("TESTER"));

        assertNotNull(userDetails);
        assertTrue(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("TESTER")));
    }
}
