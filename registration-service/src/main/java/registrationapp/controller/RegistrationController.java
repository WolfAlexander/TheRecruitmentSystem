package registrationapp.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import registrationapp.domain.UserManager;
import registrationapp.dto.UserCredentialsDTO;
import registrationapp.entity.PersonEntity;
import registrationapp.httpResponse.RegistrationResponse;
import registrationapp.inputForm.RegistrationForm;
import registrationapp.security.JwtUserDetails;

import javax.validation.Valid;
import java.util.Collection;

/**
 * REST API that is used to redirect HTTP requests to domain that handles logic. This REST API
 * will redirect requests regarding registration of a new user to the recruit system and fetching
 * of information about users
 *
 * @author Albin Friedner
 */

@RestController
@RequestMapping("/")
public class RegistrationController
{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserManager userManager;

    /**
     * Receives a HTTP Post request from a registration form. Redirects
     * the handling of the request to the domain for handling user registration.
     *
     * @param registrationForm  reference to the class that backs the form for registering a new user
     * @param bindingResult binds the results of the validation and checks for errors
     * @return a response with a HTTP status and an optional response message
     */
    @PostMapping(value = "/register")
    public RegistrationResponse register(@Valid @RequestBody RegistrationForm registrationForm, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()) {
            return new RegistrationResponse(HttpStatus.BAD_REQUEST, bindingResult.getFieldErrors());
        }else{
            try{
                userManager.register(registrationForm);
                return new RegistrationResponse(HttpStatus.CREATED);
            }catch (RuntimeException ex){
                logger.error("Unchecked exception was thrown. ", ex);
                return new RegistrationResponse(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    /**
     * Recieves a HTTP Get request from the client. Redirects the handling of the
     * request to the domain for looking up a user in the database.
     *
     * @param id The id of the user that is being looked up in the database
     * @param lang The language that the client is using
     * @return An entity representing the user being looked up
     */
    @GetMapping(value = "/{lang}/get/by/{id}")
    public PersonEntity getPersonById(@PathVariable(value = "id") int id, @PathVariable(value = "lang") String lang){
        return userManager.getUserById(id,lang);
    }

    /**
     * Recieves a HTTP Get request from the client. Redirects the handling of the
     * request to the domain for validating a user in the database.
     *
     * @param id The id of the user that is being validated
     * @param lang The language that the client is using
     * @return true if the user exists in the database. false otherwise.
     */
    @GetMapping(value = "{lang}/validate/{id}")
    public Boolean validateUserId(@PathVariable(value = "id") int id, @PathVariable(value = "lang") String lang){
        return userManager.validate(id);
    }

    /**
     * Recieves a HTTP Get request from the client. Redirects the handling of the
     * request to the domain for looking up user ids for the specified name in the
     * database
     *
     * @param lang  the language that the client is using
     * @param name  the first name of the user(s) being looked up
     * @return true if the user exists in the database. false otherwise.
     */
    @GetMapping(value = "{lang}/get/users/by/name/{name}")
    public Collection<Integer> getUserIdsByName(@PathVariable(value = "lang") String lang, @PathVariable(value = "name") String name){
        return userManager.getUserIdsByName(name);
    }

    /**
     * Recieves a HTTP Get request from the client. Redirects the handling of the
     * request to the domain for looking up a user and its credentials for a
     * specified username.
     *
     * @param lang  the language that the client is using
     * @param username  the username of the user being looked up
     * @return  the user and its credentials for the specified username
     */
    @GetMapping(value="{lang}/get/usercredentials/by/{username}")
    public JwtUserDetails getUserAndCredentialsByUsername(@PathVariable(value = "lang") String lang
            , @PathVariable(value = "username") String username)
    {
        return userManager.getUserAndCredentialsByUsername(lang, username);
    }

}
