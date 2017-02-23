package registrationapp.dao;

import registrationapp.dto.UserCredentialsDTO;
import registrationapp.entity.PersonEntity;

import java.util.Collection;
import java.util.Date;

/**
 * This is an interface for communication with a database.
 */

public interface UserServiceDao {

    void registerNewUser(String firstName, String lastName, Date dateOfBirth, String email, String username, String password);
    boolean validate(int id);
    PersonEntity getUserByIdAndLanguage(int id, String lang);
    Collection<Integer> getUserIdsByName(String name);
    UserCredentialsDTO getUserAndCredentialsByUsername(String lang, String username);
}
