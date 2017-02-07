package controller;

import jobApplicationApp.JobApplicationLauncher;
import jobApplicationApp.controller.JobApplicationController;
import jobApplicationApp.entity.*;
import jobApplicationApp.service.JobApplicationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JobApplicationLauncher.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DataJpaTest
public class JobApplicationControllerTest {

    private int applicationId;
    private Date dateOfBirth = null;
    private Date registrationDate = null;


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup(){

        try {
            dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse("1995-02-14");
            registrationDate = new SimpleDateFormat("yyyy-MM-dd").parse("2016-02-17");
        } catch (ParseException e) {
            fail("Could not create dateOfBirth from string");
        }
        ApplicationEntity application = this.entityManager.persist(
                new ApplicationEntity(new PersonEntity("Henrik","Gustavsson",dateOfBirth,"henrik.gustavsson@hotmail.com",new RoleEntity("Recruiter")),
                        new Date(),
                        new ApplicationStatusEntity("Pending"),
                        new AvailabilityEntity(),
                        new ArrayList<CompetenceProfileEntity>()));
        applicationId=application.getId();
    }


    @Test
    public void contextLoads(){
    //    assertThat(jobApplicationController).isNotNull();
    }

    @Test
    public void getApplicationById(){
        String body = this.restTemplate.getForObject("/jobapplication/1", String.class);
        assertThat(body).isEqualTo("");
    }
/**
    @Test
    public void getApplicationByParam(){
        String body = this.restTemplate.getForObject("/jobapplication/byparam", String.class);
        assertThat(body).isEqualTo("Hello World");
    }

    @Test
    public void getApplicationPage(){
        String body = this.restTemplate.getForObject("/jobapplication/page/1", String.class);
        assertThat(body).isEqualTo("Hello World");
    }

    @Test
    public void registerJobApplication(){
        ApplicationEntity a = new ApplicationEntity();
        String body = this.restTemplate.postForObject("/jobapplication",ApplicationEntity.class, a);

        assertThat(body).isEqualTo("Hello World");
    }

    @Test
    public void changeStatusOnApplicationById(){
        String body = this.restTemplate.getForObject("/jobapplication/change/status/1", String.class);
        assertThat(body).isEqualTo("Hello World");
    }

    @Test
    public void changeStatusOnApplication(){
        String body = this.restTemplate.getForObject("/jobapplication/change/status", String.class);
        assertThat(body).isEqualTo("Hello World");
    }
*/

}
