package controller;

import jobApplicationApp.JobApplicationLauncher;
import jobApplicationApp.dto.form.ApplicationParamForm;
import jobApplicationApp.dto.form.ApplicationStatusForm;
import jobApplicationApp.dto.form.AvailabilityForm;
import jobApplicationApp.dto.form.CompetenceForm;
import junit.framework.TestCase;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import static org.junit.Assert.assertEquals;





@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = JobApplicationLauncher.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JobApplicationControllerRestTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getApplicationById() {
        ResponseEntity<String> response = this.restTemplate.getForEntity("/en/by/id/2", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getApplicationByBadId() {
        ResponseEntity<String> response = this.restTemplate.getForEntity("/en/by/id/-4", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Ignore
    @Test
    public void getApplicationByParamName(){
        ApplicationParamForm inputParam = new ApplicationParamForm("henrik",null,null);
        ResponseEntity response = this.restTemplate.postForEntity("/en/by/param", inputParam, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Ignore
    @Test
    public void getApplicationByParamAvailableForWork(){
        Date readyToWorkFrom = null;
        Date readyToWorkTo = null;
        try {
            readyToWorkFrom = new SimpleDateFormat("yyyy-MM-dd").parse("1995-02-14");
            readyToWorkTo = new SimpleDateFormat("yyyy-MM-dd").parse("2016-02-17");
        } catch (ParseException e) {
            TestCase.fail("Could not create dateOfBirth from string");
        }
        AvailabilityForm af = new AvailabilityForm(readyToWorkFrom,readyToWorkTo);
        ApplicationParamForm inputParam = new ApplicationParamForm("henrik",null,null);
        ResponseEntity response = this.restTemplate.postForEntity("/en/by/param", inputParam, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getApplicationByParamNotExistingCompetence(){
        ArrayList<CompetenceForm> acf = new ArrayList<>();
        CompetenceForm cf = new CompetenceForm("dsföäsdölföalösfaäsdfläösdf",8);
        acf.add(cf);
        ApplicationParamForm inputParam = new ApplicationParamForm(null,null,acf);
        ResponseEntity response = this.restTemplate.postForEntity("/en/by/param", inputParam, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void getApplicationPage(){
        ResponseEntity<String> response = this.restTemplate.getForEntity("/jobapplication/page/0/10", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }



    @Test
    public void getApplicationPageWithBadPageNmr(){
        ResponseEntity<String> response = this.restTemplate.getForEntity("/jobapplication/page/10/-3", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void getApplicationPageWithBadPageSize(){
        ResponseEntity<String> response = this.restTemplate.getForEntity("/jobapplication/page/-3/10", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void registerJobApplicationWithNoneExistingApplication() {
        ResponseEntity response = this.restTemplate.postForEntity("/en/",null,String.class);
        assertEquals(response.getStatusCode(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @Test
    public void changeStatusOnApplicationByIdWithNoNewStatus(){
        ResponseEntity<String> response = this.restTemplate.exchange("/en/change/status/2", HttpMethod.PUT,null, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }


    @Test
    public void getAllValidStatus(){
        ResponseEntity<String> response = this.restTemplate.getForEntity("/en/getAllValidStatus", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
    @Test
    public void getAllValidCompetences(){
        ResponseEntity<String> response = this.restTemplate.getForEntity("/en/getAllValidCompetences", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

}
