package controller;

import domain.RegistrationManager;
import httpResponse.RegistrationResponse;
import inputForm.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * REST API that is used to redirect HTTP requests to domain that handles logic. This REST API
 * will redirect requests regarding registration of a new user to the recruit system.
 *
 * @author Albin Friedner
 */

@RestController
public class RegistrationController
{
    @Autowired
    RegistrationManager registrationManager;

    /**
     * Receives a HTTP Post request from a registration form. Redirects
     * the handling of the request to the domain for handling user registration.
     *
     * @param registrationForm  reference to the class that backs the form for registering a new user
     * @param bindingResult binds the results of the validation and checks for errors
     * @return a response with a HTTP status and a response message
     */
    @PostMapping("/register")
    public RegistrationResponse register(@Valid RegistrationForm registrationForm, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()) {
            return new RegistrationResponse(HttpStatus.BAD_REQUEST, bindingResult.getFieldErrors());
        }else{
            try{
                registrationManager.register(registrationForm.getFirstname(), registrationForm.getLastname(), registrationForm.getDateOfBirth()
                        , registrationForm.getEmail(), registrationForm.getUsername(), registrationForm.getPassword());

                return new RegistrationResponse(HttpStatus.CREATED);
            }catch (RuntimeException ex){
                //handle
                return null;
            }
        }
    }
}
