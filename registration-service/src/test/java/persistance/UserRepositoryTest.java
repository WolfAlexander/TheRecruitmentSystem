package persistance;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import registrationapp.RegistrationServiceApplication;
import registrationapp.entity.PersonEntity;
import registrationapp.entity.RoleEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public PersonEntity generatePersonEntity(){
        Date dateOfBirth;
        try {
            dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse("1995-02-14");
        } catch (ParseException e) {
            throw new RuntimeException("can't create person entity");
        }
        return new PersonEntity("Henrik","Gustavsson",dateOfBirth,"henrik.gustavsson@hotmail.com",generateRoleEntity());
    }

    public RoleEntity generateRoleEntity(){
        //return new LocalizedRole(new RoleEntity("Recruiter"),new LanguageEntity("sv"),"Recruiter");
        return new RoleEntity("Recruiter");
    }
/**

    @Test
    public void getPersonInformationFirstName(){
        ApplicationEntity requestedApplication = applicationRepository.findOne(applicationId);
        assertThat(requestedApplication.getPerson().getFirstname()).isEqualTo("Henrik");
    }

    @Test
    public void getPersonInformationLastName(){
        ApplicationEntity requestedApplication = applicationRepository.findOne(applicationId);
        assertThat(requestedApplication.getPerson().getLastname()).isEqualTo("Gustavsson");
    }

    @Test
    public void getPersonInformationEmail(){
        ApplicationEntity requestedApplication = applicationRepository.findOne(applicationId);
        assertThat(requestedApplication.getPerson().getDateOfBirth()).isEqualTo(dateOfBirth);
    }

    @Test
    public void getPersonInformationDateOfBirth() {
        ApplicationEntity requestedApplication = applicationRepository.findOne(applicationId);
        assertThat(requestedApplication.getPerson().getEmail()).isEqualTo("henrik.gustavsson@hotmail.com");
    }

    @Test
    public void getPersonInformationRole() {
        ApplicationEntity requestedApplication = applicationRepository.findOne(applicationId);
        assertThat(requestedApplication.getPerson().getRole().getTranslation()).isEqualTo("Recruiter");
    }
    */

}