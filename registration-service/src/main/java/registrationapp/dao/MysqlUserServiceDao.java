package registrationapp.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import registrationapp.dao.persistance.CredentialsRepository;
import registrationapp.dao.persistance.PersonRepository;
import registrationapp.dao.persistance.RoleRepository;
import registrationapp.dao.persistance.localized.LanguageRepository;
import registrationapp.dao.persistance.localized.LocalizedRoleRepository;
import registrationapp.entity.CredentialEntity;
import registrationapp.entity.PersonEntity;
import registrationapp.entity.RoleEntity;
import registrationapp.entity.localized.LanguageEntity;
import registrationapp.entity.localized.LocalizedRoleEntity;
import registrationapp.inputForm.RegistrationForm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Repository
@Qualifier("mysql")
public class MysqlUserServiceDao implements UserServiceDao {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired PersonRepository userRepository;
    @Autowired RoleRepository roleRepository;
    @Autowired CredentialsRepository credentialsRepository;
    @Autowired LocalizedRoleRepository localizedRoleRepository;
    @Autowired LanguageRepository languageRepository;


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
    @Override
    public void registerNewUser(String firstName, String lastName, Date dateOfBirth, String email, String username, String password) {
        logger.info(firstName+" "+lastName+" "+ email+ " "+username+" "+password);
        RoleEntity role = roleRepository.findOne(2);
        PersonEntity user = userRepository.save( new PersonEntity(firstName, lastName, dateOfBirth, email, role));
        CredentialEntity credential = credentialsRepository.save(new CredentialEntity(user.getId(),username,password));
    }

    @Override
    public PersonEntity getUserByIdAndLanguage(int id,String lang) {
        PersonEntity personEntity = userRepository.findOne(id);
        return translateRole(personEntity,getLanguage(lang));
    }

    @Override
    public Collection<Integer> getUserIdsByName(String name) {
        ArrayList<Integer> list = new ArrayList<>();
        for (PersonEntity p:userRepository.findByFirstName(name)) {
            list.add(p.getId());
        }userRepository.findByFirstName(name);
        return list;
    }

    private LanguageEntity getLanguage(String lang){
        return languageRepository.findByName(lang);
    }

    private PersonEntity translateRole(PersonEntity person, LanguageEntity lang){
        PersonEntity personEntity = person;
        LocalizedRoleEntity localizedRole = localizedRoleRepository.getByLanguageIdAndRoleId(lang.getId(),personEntity.getRole().getId());
        personEntity.getRole().setName(localizedRole.getTranslation());
        return personEntity;
    }

    @Override
    public boolean validate(int id) {
        return userRepository.exists(id);
    }
}
