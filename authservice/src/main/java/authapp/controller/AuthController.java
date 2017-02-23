package authapp.controller;

import authapp.security.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * Authentication controller where users can request
 *
 * @author WolfAlexander nikal@kth.se
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    private static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Generates a token when user tries to login
     * @param authRequest - user credentials
     * @throws AuthenticationException if authentication failed
     * @return ResponseEntity with a custom response and status
     */
    @PostMapping("/login")
    public ResponseEntity<?> generateJwtToken(@Valid @RequestBody AuthRequest authRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<Object>(bindingResult.getFieldErrors(), HttpStatus.BAD_REQUEST);

        try {
            performUsernamePasswordAuthentication(authRequest.getUsername(), authRequest.getPassword());
            final String jwtToken = RSAJwtTokenFactory.generateTokenForAUser(getUserDetailsByUsername(authRequest.getUsername()));

            return new ResponseEntity<Object>(new AuthTokeResponse(jwtToken), HttpStatus.OK);
        }catch (AuthenticationException authEx){
            return new ResponseEntity<Object>(new AuthFailResponse("Could not authenticate user!"), HttpStatus.UNAUTHORIZED);
        }catch (RSAJwtTokenFactoryException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Perform Spring Security authentication and adding to SecurityContext
     * @param username - entered username by user
     * @param password - entered password by user
     */
    private void performUsernamePasswordAuthentication(String username, String password) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * @param username - given username
     * @return UserDetails that contains all information about the user
     */
    private UserDetails getUserDetailsByUsername(String username){
        return userDetailsService.loadUserByUsername(username);
    }
}
