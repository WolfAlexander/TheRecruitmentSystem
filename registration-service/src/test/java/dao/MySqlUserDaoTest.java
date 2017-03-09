package dao;


import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import registrationapp.RegistrationServiceApplication;
import registrationapp.dao.MysqlUserServiceDao;
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
import registrationapp.security.JwtUserDetails;

import static org.junit.Assert.assertEquals;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

/**
 * Tests the methods of the class MySqlUserServiceDao
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = RegistrationServiceApplication.class)
@AutoConfigureTestEntityManager
public class MySqlUserDaoTest
{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    MysqlUserServiceDao mysqlUserServiceDao;

    @MockBean
    PersonRepository userRepository;

    @MockBean
    LocalizedRoleRepository localizedRoleRepository;

    @MockBean
    RoleRepository roleRepository;

    @MockBean
    CredentialsRepository credentialsRepository;

    @MockBean
    LanguageRepository languageRepository;

    @MockBean
    PersonEntity p;

    @MockBean
    LocalizedRoleEntity localizedRole;

    /**
     * Sets up the test by
     * TODO: Finish this comment
     */
    @BeforeClass
    public static void setup()
    {
        System.setProperty("spring.cloud.config.enabled", "false");
    }

    /**
     * Tests that a user can be registered successfully with valid data.
     */
    @Test
    public void registerUserTest()
    {
        given(roleRepository.findOne(2))
                .willReturn(new RoleEntity("Testrole"));

        given(userRepository.save(any(PersonEntity.class)))
                .willReturn(new PersonEntity("Test", "Testsson", new Date(1994, 3, 20)
                        , "albin@example.com", new RoleEntity("Testrole")));

        given(p.getId())
            .willReturn(22);

        given(credentialsRepository.save(any(CredentialEntity.class)))
                .willReturn(new CredentialEntity(22, "username", "password"));

        try
        {
            mysqlUserServiceDao.registerNewUser("Test", "Testsson", new Date(1994, 3, 20)
                    , "albin@example.com", "username", "password");
        }
        catch (Exception ex)
        {
            fail("Message: " +  ex.getMessage());
        }
    }


    /**
     * Tests that a user can be found by the id of the user
     */
    @Test
    @Transactional
    public void getUserByIdAndLanguageTest()
    {

        RoleEntity roleEntity = testEntityManager.persist(new RoleEntity("Testrole"));
        given(userRepository.findOne(5))
                .willReturn(new PersonEntity("Test", "Testsson", new Date(1994, 3, 20),
                        "albin@example.com", roleEntity));

        PersonEntity personEntity = userRepository.findOne(5);

        Class<MysqlUserServiceDao> classToUse;
        Method method1 = null;
        Method method2 = null;
        try
        {
            classToUse = MysqlUserServiceDao.class;
            method1 = classToUse.getDeclaredMethod("getLanguage", String.class);
            method1.setAccessible(true);
            method2 = classToUse.getDeclaredMethod("translateRole", PersonEntity.class, LanguageEntity.class);
            method2.setAccessible(true);
            given(languageRepository.findByName(any(String.class))).willReturn(new LanguageEntity(5, "sv"));
            given(localizedRoleRepository.getByLanguageIdAndRoleId(any(Integer.class), any(Integer.class)))
                    .willReturn(new LocalizedRoleEntity());
            given(localizedRole.getTranslation()).willReturn("Testroll");
            given(method1.invoke(new MysqlUserServiceDao(), "sv"))
                    .willReturn(new LanguageEntity("sv"));
            LanguageEntity languageEntity = (LanguageEntity) method1.invoke(new MysqlUserServiceDao(), "sv");
            given(method2.invoke(new MysqlUserServiceDao(), any(PersonEntity.class), any(LanguageEntity.class)))
                    .willReturn(new PersonEntity("Test", "Testsson", new Date(1994, 3, 20),
                            "albin@example.com", any(RoleEntity.class)));
        }
        catch (Exception e)
        {

        }

        PersonEntity personResponse = mysqlUserServiceDao.getUserByIdAndLanguage(5, "sv");

        assertEquals("albin@example.com", personResponse.getEmail());
    }

    /**
     * Tests that a user can be validated correctly
     */
    @Test
    public void validateUserTest()
    {
        given(userRepository.exists(5))
                .willReturn(true);

        Boolean bool = mysqlUserServiceDao.validate(5);

        assertEquals(true, bool);
    }

    /**
     * Tests that a list of userids of correct length is found when searching for a name
     */
    @Test
    public void getUserIdsByNameTest()
    {
        PersonEntity personEntity1 = new PersonEntity("Bengt", "Bengtsson", new Date(1940, 3, 21),
                "bengt@example.com", new RoleEntity("Testrole"));
        PersonEntity personEntity2 = new PersonEntity("Bengt", "Sandros", new Date(1987, 5, 2),
                "sandros@example.com", new RoleEntity("Testrole"));
        PersonEntity personEntity3 = new PersonEntity("Bengt", "Ramos", new Date(1956, 12, 13),
                "ramos@example.com", new RoleEntity("Testrole"));

        ArrayList<PersonEntity> persons = new ArrayList<>();
        persons.add(personEntity1);
        persons.add(personEntity2);
        persons.add(personEntity3);

        given(userRepository.findByFirstName("Bengt")).willReturn(persons);

        when(p.getId()).thenReturn(1).thenReturn(2).thenReturn(3);

        Collection<Integer> integers = mysqlUserServiceDao.getUserIdsByName("Bengt");

        assertEquals(3, integers.size());
    }

    /**
     * Tests if user credentials can be found by username in a correct way
     */
    @Test
    @Transactional
    public void getUserAndCredentialsByUsername()
    {
        RoleEntity roleEntity = testEntityManager.persist(new RoleEntity("Testrole"));
        given(credentialsRepository.findByUsername(any(String.class)))
                .willReturn(new CredentialEntity(5, "testuser", "testpassword"));

        given(userRepository.findOne(any(Integer.class)))
                .willReturn(new PersonEntity("test", "testsson", new Date(1994, 3, 20)
                        , "albin@example.com", roleEntity));

        JwtUserDetails jwtUserDetails = mysqlUserServiceDao.getUserAndCredentialsByUsername("testuser");

        assertEquals("testpassword", jwtUserDetails.getPassword());

    }
}
