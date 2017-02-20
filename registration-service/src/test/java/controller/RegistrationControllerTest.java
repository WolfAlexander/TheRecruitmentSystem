package controller;


import com.netflix.ribbon.proxy.annotation.Http;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import registrationapp.RegistrationServiceApplication;
import registrationapp.httpResponse.RegistrationResponse;
import registrationapp.inputForm.RegistrationForm;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RegistrationServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegistrationControllerTest {
    /*
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Ignore
    @Test
    public void successfulRegistrationTest(){
        RegistrationResponse response = this.testRestTemplate.postForObject("/register", createValidRegistrationForm(), RegistrationResponse.class);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals(null, response.getErrorList());
    }

    private RegistrationForm createValidRegistrationForm(){
        RegistrationForm validRegistrationForm = new RegistrationForm();

        validRegistrationForm.setFirstname("Test");
        validRegistrationForm.setLastname("Testlast");
       // validRegistrationForm.setDateOfBirth(LocalDate.of(1992, 12, 10));
        validRegistrationForm.setEmail("test@tst.se");
        validRegistrationForm.setUsername("Username");
        validRegistrationForm.setPassword("testtestest");

        return validRegistrationForm;
    }
    */

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void registerWithValidRegistrationFormTest()
    {
        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setFirstname("Test");
        registrationForm.setLastname("Testsson");
        registrationForm.setDateOfBirth(new Date(1994, 2, 20));
        registrationForm.setEmail("test@example.com");
        registrationForm.setUsername("test");
        registrationForm.setPassword("password123");

        RegistrationResponse registrationResponse = testRestTemplate.postForObject("/register", registrationForm,
                RegistrationResponse.class);
        assertEquals(HttpStatus.CREATED, registrationResponse.getStatus());
    }

    @Test
    public void registerWithNonValidRegistrationFormTest()
    {
        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setFirstname("T");
        registrationForm.setLastname("Te");
        registrationForm.setDateOfBirth(null);
        registrationForm.setEmail("testexamplecom");
        registrationForm.setUsername("t");
        registrationForm.setPassword("pwd");

        RegistrationResponse registrationResponse = testRestTemplate.postForObject("/register", registrationForm,
                RegistrationResponse.class);
        assertEquals(HttpStatus.BAD_REQUEST, registrationResponse.getStatus());
        assertEquals(6, registrationResponse.getErrorList().size());
    }

    /*
    @Test
    public void findUserByIdTest()
    {

    }

    @Test
    public void validateUserByIdTest()
    {

    }

    @Test
    public void getUserIdsByNameTest()
    {

    }
    */

}
