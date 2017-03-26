package authapp.service;

import authapp.security.RSAJwtTokenFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Service that performs authentication and calls needed objects to generate authorization token
 */
@Service
public class LoginService {
    private static Logger log = LoggerFactory.getLogger(LoginService.class);

    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public LoginService(UserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Performs authentication and generation of a token
     * @param username - name of the user supplied by user
     * @param password - password supplied by user
     * @return JWT(Json Web Token)
     */
    public String authenticateAndGetToken(String username, String password){
        performUsernamePasswordAuthentication(username, password);

        return RSAJwtTokenFactory.generateTokenForAUser(getAuthenticatedUserDetails());
    }

    /**
     * Perform Spring Security authentication and adding to SecurityContext
     * @param username - entered username by user
     * @param password - entered password by user
     */
    private void performUsernamePasswordAuthentication(String username, String password) {
        log.info("Performing authentication of a user with name:" + username);

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private UserDetails getAuthenticatedUserDetails(){
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
