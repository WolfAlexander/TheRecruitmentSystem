package controller;

import jobApplicationApp.JobApplicationLauncher;
import jobApplicationApp.service.JobApplicationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JobApplicationLauncher.class)
@Transactional
public class JobApplicationControllerTest {


    //Autowired
    //private TestRestTemplate restTemplate;

    //private EmbeddedDatabase db;
/**
    @Before
    public void setUp(){
        db = new EmbeddedDatabaseBuilder()
        .generateUniqueName(true)
        .addDefaultScripts()
        .build();
    }
**/


    @Autowired
    private JobApplicationService jobApplicationService;

    @Test
    public void contextLoads(){
        assertThat(jobApplicationService).isNotNull();
    }
/**
    @Test
    public void getApplicationById(){
        String body = this.restTemplate.getForObject("/jobapplication/1", String.class);
        assertThat(body).isEqualTo("");
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
