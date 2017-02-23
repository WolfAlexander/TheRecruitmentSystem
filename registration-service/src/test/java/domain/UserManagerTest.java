package domain;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import registrationapp.RegistrationServiceApplication;
import registrationapp.dao.MysqlUserServiceDao;
import registrationapp.domain.UserManager;
import registrationapp.dto.UserCredentialsDTO;
import registrationapp.entity.CredentialEntity;
import registrationapp.entity.PersonEntity;
import registrationapp.entity.RoleEntity;
import registrationapp.inputForm.RegistrationForm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Tests the methods of the class UserManager
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = RegistrationServiceApplication.class)
public class UserManagerTest
{

    @MockBean MysqlUserServiceDao mysqlUserServiceDao;
    @Autowired UserManager userManager;

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
     * Tests that a user with a valid RegistrationForm can be registered successfully to the database.
     */
    @Test
    public void registerWithValidRegistrationFormTest()
    {
        doNothing().when(mysqlUserServiceDao).registerNewUser("Test", "Testsson", new Date(1994, 3, 20),
                "albin@example.com", "username", "password");

        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setFirstname("Test");
        registrationForm.setLastname("Testsson");
        registrationForm.setDateOfBirth(new Date(1994, 3, 20));
        registrationForm.setEmail("albin@example.com");
        registrationForm.setUsername("username");
        registrationForm.setPassword("password");

        try
        {
            userManager.register(registrationForm);
        }
        catch (Exception ex)
        {
            fail("Exception was thrown");
        }
    }

    /**
     * Tests that a user can be found by the id of the user
     */
    @Test
    public void getUserByIdTest()
    {
        given(mysqlUserServiceDao.getUserByIdAndLanguage(5, "sv"))
                .willReturn(new PersonEntity("Test", "Testsson", new Date(1994, 3, 20),
                        "albin@example.com", new RoleEntity("Testrole")));

        PersonEntity personEntity = userManager.getUserById(5, "sv");
        assertEquals("albin@example.com", personEntity.getEmail());
    }

    /**
     * Tests that a user can be validated correctly
     */
    @Test
    public void validateUserTest()
    {
        given(mysqlUserServiceDao.validate(5))
                .willReturn(true);
        Boolean bool = userManager.validate(5);
        assertEquals(true, bool);
    }

    /**
     * Tests that a list of userids of correct length is found when searching for a name
     */
    @Test
    public void getUserIdsByNameTest()
    {
        ArrayList<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);
        given(mysqlUserServiceDao.getUserIdsByName("albin"))
                .willReturn(intList);
        Collection<Integer> users = userManager.getUserIdsByName("albin");
        assertEquals(3, users.size());
    }

    @Test
    public void getUserAndCredentialsByUsernameTest()
    {
        given(mysqlUserServiceDao.getUserAndCredentialsByUsername(any(String.class), any(String.class)))
                .willReturn(new UserCredentialsDTO(new PersonEntity("test", "testsson", new Date(1994, 3, 20)
                        , "albin@example.com", new RoleEntity("Testroll"))
                        , new CredentialEntity(5, "testuser", "testpassword")));

        UserCredentialsDTO userCredentialsDTO = userManager.getUserAndCredentialsByUsername("sv", "testuser");

        assertEquals("test", userCredentialsDTO.getPersonEntity().getFirstName());
        assertEquals(5, (long)userCredentialsDTO.getCredentialEntity().getPersonId());
    }
}
