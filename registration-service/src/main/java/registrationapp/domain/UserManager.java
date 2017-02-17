package registrationapp.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import registrationapp.dao.UserServiceDao;
import registrationapp.entity.PersonEntity;
import registrationapp.inputForm.RegistrationForm;

import java.util.Date;

@Service
public class UserManager {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("mysql")
    private UserServiceDao userServiceDao;

    public PersonEntity getUserById(int id, String lang) {
        return userServiceDao.getUserByIdAndLanguage(id,lang);
    }


    public void register(RegistrationForm registrationForm)
    {
       userServiceDao.registerNewUser(registrationForm.getFirstname(), registrationForm.getLastname(), registrationForm.getDateOfBirth()
               , registrationForm.getEmail(), registrationForm.getUsername(), registrationForm.getPassword());

    }

    public Boolean validate(int id) {
        return userServiceDao.validate(id);
    }
}
