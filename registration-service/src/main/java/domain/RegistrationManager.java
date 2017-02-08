package domain;

import entity.Role;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistance.RoleRepository;
import persistance.UserRepository;

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
    public void register(String firstName, String lastName, String dateOfBirth, String email, String username, String password)
    {
        Role role = roleRepository.findOne(1L);
        User user = new User(firstName, lastName, dateOfBirth, email, username, password);
        role.addUser(user);
        userRepository.save(user);
    }
}
