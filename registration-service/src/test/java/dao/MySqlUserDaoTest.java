package dao;


import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import registrationapp.RegistrationServiceApplication;
import registrationapp.dao.MysqlUserServiceDao;
import registrationapp.dao.persistance.PersonRepository;
import registrationapp.dao.persistance.localized.LocalizedRoleRepository;
import registrationapp.entity.PersonEntity;
import registrationapp.entity.RoleEntity;
import registrationapp.entity.localized.LocalizedRoleEntity;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static org.mockito.BDDMockito.given;

/**
 * Tests the methods of the class MySqlUserServiceDao
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RegistrationServiceApplication.class)
public class MySqlUserDaoTest
{
    @Autowired
    MysqlUserServiceDao mysqlUserServiceDao;

    @Mock
    PersonRepository personRepository;

    @Mock
    LocalizedRoleRepository localizedRoleRepository;

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

    }


    /**
     * Tests that a user can be found by the id of the user
     */
    @Test
    public void getUserByIdAndLanguageTest()
    {
        given(personRepository.findOne(5))
                .willReturn(new PersonEntity("Test", "Testsson", new Date(1994, 3, 20),
                "albin@example.com", new RoleEntity("Testrole")));

        given(localizedRoleRepository.getByLanguageIdAndRoleId(1, 2))
                .willReturn(new LocalizedRoleEntity());
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

        given(personRepository.findByFirstName("Bengt")).willReturn(persons);


        Collection<Integer> integers = mysqlUserServiceDao.getUserIdsByName("Bengt");

        assertEquals(3, integers.size());
    }
}
