package java.controller;


import static org.junit.Assert.assertEquals;
/*
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RegistrationServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)*/
public class RegistrationControllerTest {
   /* @Autowired
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
    }*/
}
