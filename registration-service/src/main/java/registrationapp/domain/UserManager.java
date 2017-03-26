package registrationapp.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import registrationapp.dao.UserServiceDao;
import registrationapp.dto.UserCredentialsDTO;
import registrationapp.entity.PersonEntity;
import registrationapp.inputForm.RegistrationForm;
import registrationapp.security.JwtUserDetails;

import java.util.Collection;

/**
 * This is a service that handles the logic regarding the rectuitment
 * system.
 */

@Service
public class UserManager {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("mysql")
    private UserServiceDao userServiceDao;

    /**
     * Gets a user from a database on ID. The role is translated to the language that the client uses.
     *
     * @param id The id of a user registered in the database
     * @param lang  The language used by the client that currently interacts with the service
     * @return  An Entity of the person with the specified ID
     */
    public PersonEntity getUserById(int id, String lang) {

        PersonEntity personEntity = userServiceDao.getUserByIdAndLanguage(id,lang);
        if(personEntity != null)
        {
            logger.info("User with ID: " + personEntity.getId() + " has been looked up in the database by ID.");
        }
        return personEntity;
    }

    /**
     * Registers a new user to the database
     *
     * @param registrationForm reference to the class that backs the form for registering a new user
     */
    public void register(RegistrationForm registrationForm)
    {
       userServiceDao.registerNewUser(registrationForm.getFirstname(), registrationForm.getLastname(), registrationForm.getDateOfBirth()
               , registrationForm.getEmail(), registrationForm.getUsername(), registrationForm.getPassword());

       logger.info("New user with username :" + registrationForm.getUsername() + " has been registered to the database.");
    }

    /**
     * Validates if a user exists in the database
     *
     * @param id The id of the user that is being validated
     * @return true if the user exists in the database. false otherwise
     */
    public Boolean validate(int id)
    {
        Boolean bool = userServiceDao.validate(id);
        if(bool)
        {
            logger.info("User with ID " + id + " has been validated. User was found in the database.");
        }
        else
        {
            logger.info("User with ID " + id + " has been validated. User was not found in the database.");
        }
        return bool;
    }

    /**
     * Gets ids of users based on the first name of the user
     *
     * @param name The first name of the user(s) that is being looked up
     * @return  A Collection of the user ids that matches the specified first name
     */
    public Collection<Integer> getUserIdsByName(String name)
    {
        Collection<Integer> userIDs = userServiceDao.getUserIdsByName(name);
        for(Integer userID: userIDs)
        {
            logger.info("User with ID " + userID + " was looked up in the database by the name " + name);
        }
        return userIDs;
    }

    /**
     * Gets a user and credentials for a specified username and language.
     *
     * @param username  the username that is being looked up
     * @return  a DTO that encapsulate a PersonEntity and a CredentialsEntity
     */
    public JwtUserDetails getUserAndCredentialsByUsername(String username)
    {
        return userServiceDao.getUserAndCredentialsByUsername(username);
    }
}
