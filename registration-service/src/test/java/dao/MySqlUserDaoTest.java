package dao;


import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
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
import static org.junit.Assert.assertEquals;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

/**
 * Tests the methods of the class MySqlUserServiceDao
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = RegistrationServiceApplication.class)
public class MySqlUserDaoTest
{
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
    PersonEntity user;

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

        RoleEntity roleEntity = roleRepository.findOne(2);
        System.out.println("Roleentity id " + roleEntity.getId());
        given(userRepository.save(new PersonEntity("Test", "Testsson", new Date(1994, 3, 20)
                , "albin@example.com", new RoleEntity("Testrole"))))
                .willReturn(new PersonEntity("Test", "Testsson", new Date(1994, 3, 20)
                        , "albin@example.com", roleEntity));

        given(user.getId())
            .willReturn(22);

        given(credentialsRepository.save(new CredentialEntity(user.getId(), "username", "password")))
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
    public void getUserByIdAndLanguageTest()
    {
        given(userRepository.findOne(5))
                .willReturn(new PersonEntity("Test", "Testsson", new Date(1994, 3, 20),
                "albin@example.com", new RoleEntity("Testrole")));

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
            given(languageRepository.findByName("sv"))
                    .willReturn(new LanguageEntity("sv"));
            given(method1.invoke(new MysqlUserServiceDao(), "sv"))
                    .willReturn(new LanguageEntity("sv"));
            LanguageEntity languageEntity = (LanguageEntity) method1.invoke(new MysqlUserServiceDao(), "sv");
            given(method2.invoke(new MysqlUserServiceDao(), personEntity, languageEntity))
                    .willReturn(new PersonEntity("Test", "Testsson", new Date(1994, 3, 20),
                            "albin@example.com", new RoleEntity("Testroll")));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        PersonEntity personResponse = mysqlUserServiceDao.getUserByIdAndLanguage(5, "sv");

        assertEquals("albin@example.com", personResponse.getEmail());
        assertEquals("Testroll", personResponse.getRole().getName());
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

        PersonEntity p = new PersonEntity();

        doReturn(1)
                .doReturn(2)
                .doReturn(3)
                .when(p).getId();

        Collection<Integer> integers = mysqlUserServiceDao.getUserIdsByName("Bengt");

        assertEquals(3, integers.size());
    }
}
