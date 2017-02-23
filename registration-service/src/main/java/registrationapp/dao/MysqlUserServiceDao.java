package registrationapp.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;
import registrationapp.dao.persistance.CredentialsRepository;
import registrationapp.dao.persistance.PersonRepository;
import registrationapp.dao.persistance.RoleRepository;
import registrationapp.dao.persistance.localized.LanguageRepository;
import registrationapp.dao.persistance.localized.LocalizedRoleRepository;
import registrationapp.dto.UserCredentialsDTO;
import registrationapp.entity.CredentialEntity;
import registrationapp.entity.PersonEntity;
import registrationapp.entity.RoleEntity;
import registrationapp.entity.localized.LanguageEntity;
import registrationapp.entity.localized.LocalizedRoleEntity;
import registrationapp.security.JwtUserDetails;
import sun.text.resources.ro.CollationData_ro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class implements the UserServiceDao and therefore handles the communication with the
 * database used in the recruitment system.
 *
 */

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

    /**
     * Gets a user from a database on ID. The role is translated to the language that the client uses.
     *
     * @param id The id of a user registered in the database
     * @param lang  The language used by the client that currently interacts with the service
     * @return  An Entity of the person with the specified ID
     */
    @Override
    public PersonEntity getUserByIdAndLanguage(int id,String lang) {
        PersonEntity personEntity = userRepository.findOne(id);
        return translateRole(personEntity,getLanguage(lang));
    }

    /**
     * Gets ids of users based on the first name of the user
     *
     * @param name The first name of the user(s) that is being looked up
     * @return  A Collection of the user ids that matches the specified first name
     */
    @Override
    public Collection<Integer> getUserIdsByName(String name) {
        ArrayList<Integer> list = new ArrayList<>();
        for (PersonEntity p: userRepository.findByFirstName(name))
        {
            list.add(p.getId());
        }
        userRepository.findByFirstName(name);
        return list;
    }

    /**
     * Gets a user and credentials for a specified username and language.
     *
     * @param lang  the language that the client is using
     * @param username  the username that is being looked up
     * @return  a DTO that encapsulate a PersonEntity and a CredentialsEntity
     */
    @Override
    public JwtUserDetails getUserAndCredentialsByUsername(String lang, String username) {
        CredentialEntity credentialEntity = credentialsRepository.findByUsername(username);
        PersonEntity personEntity = userRepository.findOne(credentialEntity.getPersonId());
        List<RoleEntity> roleEntities = new ArrayList<>();
        roleEntities.add(personEntity.getRole());
        Collection<GrantedAuthority> roles = mapToGrantedAuthorities(roleEntities);
        JwtUserDetails jwtUserDetails = new JwtUserDetails((long)credentialEntity.getPersonId(), credentialEntity.getUsername()
                ,credentialEntity.getPassword(), roles);
        return jwtUserDetails;
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

    /**
     * Validates if a user exists in the database
     *
     * @param id The id of the user that is being validated
     * @return true if the user exists in the database. false otherwise
     */
    @Override
    public boolean validate(int id) {
        return userRepository.exists(id);
    }

    private List<GrantedAuthority> mapToGrantedAuthorities(List<RoleEntity> roles){
        return roles.stream()
                .map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList());
    }
}
