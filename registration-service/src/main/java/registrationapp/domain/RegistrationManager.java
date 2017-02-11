package registrationapp.domain;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registrationapp.entity.Credential;
import registrationapp.entity.Role;
import registrationapp.entity.User;
import registrationapp.persistance.CredentialsRepository;
import registrationapp.persistance.RoleRepository;
import registrationapp.persistance.UserRepository;


import java.time.LocalDate;
import java.util.Date;

/**
 * This class is used as a logic service that is handling registration of a new user
 * to the recruit system
 *
 * @author Albin Friedner
 */

@Service
public class RegistrationManager
{
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CredentialsRepository credentialsRepository;
    /**
     * Checks if a user, that tries to register, is already registered in the database.
     * (NOT IMPLEMENTED YET)
     *
     * @param username the username of the user that tries to register
     * @return true if the user exists in the database, false otherwise
     */
    /*
    public boolean checkUser(String username)
    {
        return true;
    }
    */

    /**
     * Adds a new user to an external database and assigns a role to that user
     *
     * @param firstName the first name of the user that is added to the database
     * @param lastName  the last name of the user that is added to the database
     * @param dateOfBirth   the social security number of the user that is added to the database
     * @param email the email address of the user that is added to the database
     * @param username  the username of the user that is added to the database
     * @param password  the password of the user that is added to the database
     */
    public void register(String firstName, String lastName, Date dateOfBirth, String email, String username, String password)
    {
        logger.info(firstName+" "+lastName+" "+ email+ " "+username+" "+password);
        Role role = roleRepository.findOne(2L);
        User user = userRepository.save( new User(firstName, lastName, dateOfBirth, email, role));
        Credential credential = credentialsRepository.save(new Credential(user.getId(),username,password));

    }
}
