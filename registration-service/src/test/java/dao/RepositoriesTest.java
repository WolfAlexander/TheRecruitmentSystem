package dao;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import registrationapp.RegistrationServiceApplication;
import registrationapp.dao.persistance.CredentialsRepository;
import registrationapp.dao.persistance.PersonRepository;
import registrationapp.dao.persistance.RoleRepository;
import registrationapp.entity.CredentialEntity;
import registrationapp.entity.PersonEntity;
import registrationapp.entity.RoleEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Tests the different repositories and their methods
 */

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = RegistrationServiceApplication.class)
@DataJpaTest
public class RepositoriesTest
{
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CredentialsRepository credentialsRepository;

    @BeforeClass
    public static void setup()
    {
        System.setProperty("spring.cloud.config.enabled", "false");

    }

    @Test
    public void addUserToDatabaseTest()
    {
        testEntityManager.persist(new RoleEntity("Testrole"));
        RoleEntity roleEntity = roleRepository.findOne(1);
        PersonEntity personEntity = personRepository.save(new PersonEntity("Test", "Testsson", new Date(1994, 3, 20)
                , "albin@example.com", roleEntity));
        credentialsRepository.save(new CredentialEntity(personEntity.getId(), "testusername", "testpassword"));
        assertThat(personRepository.findOne(1)).isNotNull();
        assertThat(credentialsRepository.findOne(1)).isNotNull();
    }

    @Test
    public void findUsersByFirstNameTest()
    {
        testEntityManager.persist(new RoleEntity("Testrole"));
        RoleEntity roleEntity = roleRepository.findOne(1);
        PersonEntity personEntity = personRepository.save(new PersonEntity("Test", "Testsson", new Date(1994, 3, 20)
                , "albin@example.com", roleEntity));
        credentialsRepository.save(new CredentialEntity(personEntity.getId(), "testusername", "testpassword"));
        Collection<PersonEntity> personEntities = personRepository.findByFirstName("Test");
        assertEquals(1, personEntities.size());
    }
}
