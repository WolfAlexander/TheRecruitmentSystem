package controller;

import jobApplicationApp.JobApplicationLauncher;
import jobApplicationApp.controller.JobApplicationController;
import jobApplicationApp.dto.form.*;
import jobApplicationApp.exception.NotValidArgumentException;
import jobApplicationApp.service.JobApplicationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindException;
import utils.JobApplicationFormGenerater;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;


@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = JobApplicationLauncher.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JobApplicationControllerMockTest {
    @Autowired
    private JobApplicationController jobApplicationController;

    @MockBean
    private JobApplicationService jobApplicationService;


    JobApplicationFormGenerater jobApplicationFormGenerater = new JobApplicationFormGenerater();

    @Test
    public void getAllValidStatusErrorHandlingTest(){

        given(jobApplicationService.getAllValidStatus("en")).willThrow(new NotValidArgumentException("oh no"));
        ResponseEntity responseEntity = jobApplicationController.getAllValidStatus("en");
        assertEquals(responseEntity.getStatusCode(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Test
    public void getAllValidCompetencesErrorHandlingTest(){
        given(jobApplicationService.getAllValidCompetences("en")).willThrow(new NotValidArgumentException("oh no"));
        ResponseEntity responseEntity = jobApplicationController.getAllValidCompetences("en");
        assertEquals(responseEntity.getStatusCode(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Test
    public void ChangeStatusErrorErrorResponse(){
        ApplicationStatusForm applicationStatusForm = new ApplicationStatusForm("jek");

        doThrow(new NotValidArgumentException("oh no")).when(jobApplicationService).changeStatusOnApplicationById(1,applicationStatusForm);
        ResponseEntity responseEntity = jobApplicationController.changeStatusOnApplicationById(1,applicationStatusForm,"en",new BindException(ApplicationParamForm.class,""));
        assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
    }


    @Test
    public void ChangeStatus(){
        ApplicationStatusForm applicationStatusForm = new ApplicationStatusForm("jek");
        ResponseEntity responseEntity = jobApplicationController.changeStatusOnApplicationById(1,applicationStatusForm,"en",new BindException(ApplicationParamForm.class,""));
        assertEquals(HttpStatus.ACCEPTED,responseEntity.getStatusCode());
    }


    @Test
    public void insertNewApplication(){
        ResponseEntity responseEntity = jobApplicationController.registerJobApplication(jobApplicationFormGenerater.generateApplicationForm(),"en",new BindException(ApplicationParamForm.class,""));
        assertEquals(HttpStatus.ACCEPTED,responseEntity.getStatusCode());
    }

    @Test
    public void insertNewApplicationErrorResponse(){
        ApplicationForm applicationForm = jobApplicationFormGenerater.generateApplicationForm();
        doThrow(new NotValidArgumentException("oh no")).when(jobApplicationService).registerJobApplication(applicationForm);
        ResponseEntity responseEntity = jobApplicationController.registerJobApplication(applicationForm,"en",new BindException(ApplicationParamForm.class,""));
        assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
    }

}
