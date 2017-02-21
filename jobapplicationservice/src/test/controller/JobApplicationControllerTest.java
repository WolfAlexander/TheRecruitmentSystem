package controller;


import jobApplicationApp.JobApplicationLauncher;
import jobApplicationApp.dto.form.ApplicationForm;
import jobApplicationApp.dto.form.ApplicationParamForm;
import jobApplicationApp.dto.form.CompetenceForm;
import jobApplicationApp.exception.NotValidArgumentException;
import jobApplicationApp.service.JobApplicationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import utils.JobApplicationEntityGenerater;
import utils.JobApplicationFormGenerater;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = JobApplicationLauncher.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JobApplicationControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    JobApplicationFormGenerater jobApplicationFormGenerater = new JobApplicationFormGenerater();
    JobApplicationEntityGenerater jobApplicationEntityGenerater = new JobApplicationEntityGenerater();

    @MockBean
    private JobApplicationService jobApplicationService;

    @Test
    public void getApplicationById() {
        given(jobApplicationService.getApplicationById(1,"en")).willReturn(jobApplicationFormGenerater.generateApplicationResponse());
        ResponseEntity<String> response = this.restTemplate.getForEntity("/en/by/id/1", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getApplicationByBadId() {
        given(jobApplicationService.getApplicationById(-1,"en")).willThrow(new NotValidArgumentException("oh no"));
        ResponseEntity<String> response = this.restTemplate.getForEntity("/en/by/id/-1", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void getApplicationByParamNotExistingCompetence(){
        ArrayList<CompetenceForm> acf = new ArrayList<>();
        CompetenceForm cf = new CompetenceForm("dsföäsdölföalösfaäsdfläösdf",8);
        acf.add(cf);
        ApplicationParamForm inputParam = new ApplicationParamForm(null,null,acf);
        ResponseEntity response = this.restTemplate.postForEntity("/en/by/param", inputParam, String.class);
        assertEquals(response.getStatusCode(),HttpStatus.OK);
    }

    @Test
    public void getApplicationPage(){
        given(jobApplicationService.getApplicationsPage(0,10,"en")).willReturn(jobApplicationEntityGenerater.generateApplicationEntityList());
        ResponseEntity<String> response = this.restTemplate.getForEntity("/jobapplication/page/0/10", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void registerJobApplicationWithNoneExistingApplication() {
        ResponseEntity response = this.restTemplate.postForEntity("/en/",null,String.class);
        assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, response.getStatusCode());
    }

    @Test
    public void changeStatusOnApplicationByIdWithNoNewStatus(){
        ResponseEntity<String> response = this.restTemplate.exchange("/en/change/status/2", HttpMethod.PUT,null, String.class);
        assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, response.getStatusCode());
    }

    @Test
    public void getAllValidStatus(){
        given(jobApplicationService.getAllValidStatus("en")).willReturn(jobApplicationEntityGenerater.generateListOfApplicationStatus());
        ResponseEntity<String> response = this.restTemplate.getForEntity("/en/getAllValidStatus", String.class);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }
    @Test
    public void getAllValidCompetences(){
        given(jobApplicationService.getAllValidCompetences("en")).willReturn(jobApplicationEntityGenerater.generateListOfCompetences());
        ResponseEntity<String> response = this.restTemplate.getForEntity("/en/getAllValidCompetences", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
