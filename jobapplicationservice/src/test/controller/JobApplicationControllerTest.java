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
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;


public class JobApplicationControllerTest {

    @Test
    public void contextLoads(){
    fail("s");
    }
/**
    @Test
    public void getApplicationById(){
    }

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
