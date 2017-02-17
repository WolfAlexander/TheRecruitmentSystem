package registrationapp.dao;

import registrationapp.entity.PersonEntity;
import registrationapp.inputForm.RegistrationForm;

import java.util.Date;

public interface UserServiceDao {

    void registerNewUser(String firstName, String lastName, Date dateOfBirth, String email, String username, String password);
    boolean validate(int id);
    PersonEntity getUserByIdAndLanguage(int id, String lang);
}
