package persistance;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import registrationapp.RegistrationServiceApplication;

/*
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RegistrationServiceApplication.class)*/
//@DataJpaTest
public class UserRepositoryTest{
/*
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;*/
    /*
    @Test
    public void testRegisterUserWithOKParameters() throws Exception
    {
        Role role = roleRepository.findOne(1L);
        this.entityManager.persist(new User("Test", "Testson", "000000-1234", "testson@test.com"
                , role, "testson", "pwd"));
        User user = this.userRepository.findByUsername("testson").get(0);
        assertEquals(user.getFirstName(),"Test");
        assertEquals(user.getLastName(), "Testson");
        assertEquals(user.getDateOfBirth(), "000000-1234");
        assertEquals(user.getRole().getRoleId(), (Long)1L);
        assertEquals(user.getUsername(), "testson");
        assertEquals(user.getPassword(),"pwd");
    }
*/
}