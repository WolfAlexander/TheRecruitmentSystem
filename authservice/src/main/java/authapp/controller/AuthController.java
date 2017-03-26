package authapp.controller;

import authapp.dto.AuthFailResponse;
import authapp.dto.AuthRequest;
import authapp.dto.AuthTokeResponse;
import authapp.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
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
    private static Logger log = LoggerFactory.getLogger(AuthController.class);

    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final LoginService loginService;

    /**
     * Injecting dependencies
     * @param userDetailsService - UserDetailsService instance
     * @param authenticationManager - AuthenticationManager instance
     * @param loginService - LoginService instance
     */
    @Autowired
    public AuthController(UserDetailsService userDetailsService, AuthenticationManager authenticationManager, LoginService loginService) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.loginService = loginService;

        log.info("Auth Controller initialized");
    }

    /**
     * Generates a token when user tries to login
     * @param authRequest - user credentials
     * @throws AuthenticationException if authentication failed
     * @return ResponseEntity with a custom response and status
     */
    @PostMapping("/login")
    public ResponseEntity<?> generateJwtToken(@Valid @RequestBody AuthRequest authRequest, BindingResult bindingResult){
        log.info("/login received request");

        if(bindingResult.hasErrors()){
            log.warn("Request contains user input errors: " + bindingResult.getFieldErrors());
            return new ResponseEntity<Object>(bindingResult.getFieldErrors(), HttpStatus.BAD_REQUEST);
        }

        log.info("Request does not contain user input errors");

        try {
            String jwtToken = loginService.authenticateAndGetToken(authRequest.getUsername(), authRequest.getPassword());

            log.info("Authentication passed and token created!");
            return new ResponseEntity<Object>(new AuthTokeResponse(jwtToken), HttpStatus.OK);
        }catch (UsernameNotFoundException ex) {
            log.warn(ex.getMessage(), ex);
            return new ResponseEntity<Object>(new AuthFailResponse(ex.getMessage()), HttpStatus.UNAUTHORIZED);
        }catch (BadCredentialsException ex){
            log.warn("User could not authenticate: Bad credentials");
            return new ResponseEntity<Object>(new AuthFailResponse("User could not authenticate: Bad credentials"), HttpStatus.UNAUTHORIZED);
        }catch (Exception ex){
            log.error("SERVICE_ERROR", "Serious service error occurred! ", ex);
            return new ResponseEntity<Object>(new AuthFailResponse("Error occurred on the service"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
