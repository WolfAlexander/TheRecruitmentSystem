package controller;

import jobApplicationApp.JobApplicationLauncher;
import jobApplicationApp.controller.JobApplicationController;
import jobApplicationApp.dto.form.ApplicationParamForm;
import jobApplicationApp.dto.form.AvailabilityForm;
import jobApplicationApp.dto.form.CompetenceForm;
import jobApplicationApp.exception.NotValidArgumentException;
import jobApplicationApp.service.JobApplicationService;
import junit.framework.TestCase;
import org.junit.Ignore;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = JobApplicationLauncher.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JobApplicationControllerMockTest {
    @Autowired
    private JobApplicationController jobApplicationController;

    @MockBean
    private JobApplicationService jobApplicationService;

    @Test
    public void getAllValidStatusErrorHandlingTest(){

        given(jobApplicationService.getAllValidStatus()).willThrow(new NotValidArgumentException("oh no"));
        ResponseEntity responseEntity = jobApplicationController.getAllValidStatus();
        assertEquals(responseEntity.getStatusCode(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Test
    public void getAllValidCompetencesErrorHandlingTest(){
        given(jobApplicationService.getAllValidCompetences()).willThrow(new NotValidArgumentException("oh no"));
        ResponseEntity responseEntity = jobApplicationController.getAllValidCompetences();
        assertEquals(responseEntity.getStatusCode(), HttpStatus.SERVICE_UNAVAILABLE);
    }

}
