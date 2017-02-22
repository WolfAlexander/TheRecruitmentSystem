package controller;


import jobApplicationApp.JobApplicationLauncher;
import jobApplicationApp.dto.form.ApplicationForm;
import jobApplicationApp.dto.form.ApplicationParamForm;
import jobApplicationApp.dto.form.CompetenceForm;
import jobApplicationApp.exception.NotValidArgumentException;
import jobApplicationApp.service.JobApplicationService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import utils.JobApplicationEntityGenerator;
import utils.JobApplicationFormGenerater;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doNothing;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = JobApplicationLauncher.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JobApplicationControllerTest {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TestRestTemplate restTemplate;

    private JobApplicationFormGenerater jobApplicationFormGenerater = new JobApplicationFormGenerater();
    private JobApplicationEntityGenerator jobApplicationEntityGenerator = new JobApplicationEntityGenerator();

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
        given(jobApplicationService.getApplicationsPage(0,10,"en")).willReturn(jobApplicationEntityGenerator.generateApplicationEntityList());
        ResponseEntity<String> response = this.restTemplate.getForEntity("/en/page/0/10", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getBadApplicationPage(){
        given(jobApplicationService.getApplicationsPage(0,-10,"en")).willThrow(new NotValidArgumentException("oh no"));
        ResponseEntity<String> response = this.restTemplate.getForEntity("/en/page/0/-10", String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


    @Test
    public void registerJobApplicationWithNoneExistingApplication() {
        ResponseEntity response = this.restTemplate.postForEntity("/en/",null,String.class);
        assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, response.getStatusCode());
    }



    @Test
    public void registerJobApplication() {
        ApplicationForm application = jobApplicationFormGenerater.generateApplicationForm();
        doNothing().when(jobApplicationService).registerJobApplication(any(ApplicationForm.class),anyString());

        ResponseEntity response = this.restTemplate.exchange("/en/",HttpMethod.POST,generateJsonApplicationForm(1,1426291200000L,1463011200000L), String.class);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }
    @Test

    public void registerJobApplicationWithTooLowId() {
        ApplicationForm application = jobApplicationFormGenerater.generateApplicationForm();
        doNothing().when(jobApplicationService).registerJobApplication(any(ApplicationForm.class),anyString());
        ResponseEntity response = this.restTemplate.exchange("/en/",HttpMethod.POST,generateJsonApplicationForm(-1,1426291200000L,1463011200000L), String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("{\"messages\":[\"must be greater than or equal to 0\"]}",response.getBody().toString());
    }

    @Test
    public void changeApplicationStatus() {
        ApplicationForm application = jobApplicationFormGenerater.generateApplicationForm();
        doNothing().when(jobApplicationService).changeStatusOnApplicationById(anyInt(),any(),anyString());
        ResponseEntity response = this.restTemplate.exchange("/en/change/status/9",HttpMethod.PUT,generateJsonStatusForm("PENDING"), String.class);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    public void changeApplicationStatusWithoutAnyNameOnStatus() {
        ApplicationForm application = jobApplicationFormGenerater.generateApplicationForm();
        doNothing().when(jobApplicationService).changeStatusOnApplicationById(anyInt(),any(),anyString());
        ResponseEntity response = this.restTemplate.exchange("/en/change/status/9",HttpMethod.PUT,generateJsonStatusForm(null), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    private HttpEntity<String> generateJsonStatusForm(String name){
        JSONObject request = new JSONObject();
        request.put("name", name);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(request.toJSONString(), headers);
        return entity;
    }

    private HttpEntity<String> generateJsonApplicationForm(int personId, Long fromDate, Long toDate){
        JSONObject request = new JSONObject();
        request.put("personId", personId);
        JSONObject availableForWork = new JSONObject();
        availableForWork.put("fromDate",fromDate);
        availableForWork.put("toDate", toDate);
        request.put("availableForWork", availableForWork);
        JSONArray jsonArray = new JSONArray();
        CompetenceForm competenceForm = jobApplicationFormGenerater.generateCompetenceForm();

        JSONObject competences = new JSONObject();
        competences.put("name",competenceForm.getName());
        competences.put("yearsOfExperience",9);

        jsonArray.add(competences);

        request.put("competenceProfile",jsonArray);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(request.toJSONString(), headers);
        return entity;
    }



    @Test
    public void changeStatusOnApplicationByIdWithNoNewStatus(){
        ResponseEntity<String> response = this.restTemplate.exchange("/en/change/status/2", HttpMethod.PUT,null, String.class);
        assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, response.getStatusCode());
    }

    @Test
    public void changeStatusOnApplicationById(){
        ResponseEntity<String> response = this.restTemplate.exchange("/en/change/status/2", HttpMethod.PUT,null, String.class);
        assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, response.getStatusCode());
    }

    @Test
    public void getAllValidStatus(){
        given(jobApplicationService.getAllValidStatus("en")).willReturn(jobApplicationEntityGenerator.generateListOfApplicationStatus());
        ResponseEntity<String> response = this.restTemplate.getForEntity("/en/getAllValidStatus", String.class);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void getAllValidStatusBadLanguage(){
        given(jobApplicationService.getAllValidStatus("sdfsdfsdf")).willThrow(new NotValidArgumentException("oh no"));
        ResponseEntity<String> response = this.restTemplate.getForEntity("/sdfsdfsdf/getAllValidStatus", String.class);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }

    @Test
    public void getAllValidCompetences(){
        given(jobApplicationService.getAllValidCompetences("en")).willReturn(jobApplicationEntityGenerator.generateListOfCompetences());
        ResponseEntity<String> response = this.restTemplate.getForEntity("/en/getAllValidCompetences", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    public void getAllValidCompetencesBadLanguage(){
        given(jobApplicationService.getAllValidCompetences("asdasdasd")).willThrow(new NotValidArgumentException("oh no"));
        ResponseEntity<String> response = this.restTemplate.getForEntity("/asdasdasd/getAllValidCompetences", String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
