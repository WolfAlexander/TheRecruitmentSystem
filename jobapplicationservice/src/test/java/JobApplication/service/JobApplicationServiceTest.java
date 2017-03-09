package JobApplication.service;

import JobApplication.utils.JobApplicationEntityGenerator;
import JobApplication.utils.JobApplicationFormGenerater;
import jobApplicationApp.JobApplicationLauncher;
import jobApplicationApp.dao.MysqlApplicationDao;
import jobApplicationApp.dto.form.ApplicationParamForm;
import jobApplicationApp.dto.form.ApplicationStatusForm;
import jobApplicationApp.dto.form.PersonForm;
import jobApplicationApp.dto.form.RoleForm;
import jobApplicationApp.dto.response.ApplicationResponse;
import jobApplicationApp.entity.*;
import jobApplicationApp.exception.NotValidArgumentException;
import jobApplicationApp.service.JobApplicationService;
import jobApplicationApp.service.UserApi;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobApplicationLauncher.class)
@ActiveProfiles("testing")
public class JobApplicationServiceTest {

    private JobApplicationFormGenerater jobApplicationFormGenerater = new JobApplicationFormGenerater();
    private JobApplicationEntityGenerator jobApplicationEntityGenerator = new JobApplicationEntityGenerator();

    @MockBean
    private MysqlApplicationDao mysqlApplicationDao;

    @MockBean
    private UserApi userApi;

    @Autowired
    JobApplicationService jobApplicationService;

    @Test
    public void getAllValidCompetences(){
        Collection<CompetenceEntity> collection = new ArrayList<>();
        collection.add(new CompetenceEntity("super skills"));
        collection.add(new CompetenceEntity("bad skills"));
        given(this.mysqlApplicationDao.getAllValidCompetences("en")).willReturn(collection);
        Collection<CompetenceEntity> returnList = jobApplicationService.getAllValidCompetences("en");
        collection.forEach((v)->{
            assertThat(returnList).contains(v);
        });
    }

    @Test
    public void getAllValidStatus() {
        Collection<ApplicationStatusEntity> collection = new ArrayList<>();
        collection.add(new ApplicationStatusEntity("PENDING"));
        collection.add(new ApplicationStatusEntity("ACCEPTED"));
        given(this.mysqlApplicationDao.getAllValidStatus("en")).willReturn(collection);
        Collection<ApplicationStatusEntity> returnList = jobApplicationService.getAllValidStatus("en");
        collection.forEach((v)->{
            assertThat(returnList).contains(v);
        });
    }

    @Test
    public void getApplicationById() {
        ApplicationEntity  applicationEntity  = jobApplicationEntityGenerator.generateApplicationEntity();
        given(userApi.getUserById(anyInt())).willReturn( new PersonForm("Adrian","Gortzak",new Date(2016,02,17),"addegor@hotmail.com",new RoleForm("test")));
        given(this.mysqlApplicationDao.getApplicationById(1,"en")).willReturn(applicationEntity);
        ApplicationResponse returnApplication = jobApplicationService.getApplicationById(1,"en");
        assertEquals(returnApplication.getStatus().getName(), "PENDING");
    }

    @Test
    public void getApplicationByBadId() {
        ApplicationEntity  applicationEntity  = jobApplicationEntityGenerator.generateApplicationEntity();
        given(this.mysqlApplicationDao.getApplicationById(-1,"en")).willReturn(applicationEntity);
        try{
            ApplicationResponse returnApplication = jobApplicationService.getApplicationById(-2,"en");
            fail("Not allowed id was accepted");
        }catch (NotValidArgumentException e){}
    }


    @Test
    public void getNoneExistingApplicationById() {
        given(this.mysqlApplicationDao.getApplicationById(1,"en")).willReturn(new ApplicationEntity());
        ApplicationResponse returnApplication = jobApplicationService.getApplicationById(1,"en");
        given(userApi.getUserById(anyInt())).willReturn( new PersonForm("Adrian","Gortzak",new Date(2016,02,17),"addegor@hotmail.com",new RoleForm("test")));
        assertThat(returnApplication.getAvailableForWork()).isEqualTo(null);
        assertThat(returnApplication.getCompetenceProfile()).isEqualTo(null);
        assertThat(returnApplication.getDateOfRegistration()).isEqualTo(null);
    }

    @Test
    public void getApplicationsByParam() {
        ApplicationParamForm applicationParamForm = new ApplicationParamForm("Sven",null,null);
        Collection<ApplicationEntity> collection = new ArrayList<>();
        given(userApi.getUserById(anyInt())).willReturn( new PersonForm("Adrian","Gortzak",new Date(2016,02,17),"addegor@hotmail.com",new RoleForm("test")));
        collection.add(new ApplicationEntity());//1
        collection.add(new ApplicationEntity());//2
        collection.add(new ApplicationEntity());//3
        collection.add(new ApplicationEntity());//4
        given(this.mysqlApplicationDao.getApplicationByParam(applicationParamForm, "en")).willReturn(collection);
        Collection<ApplicationResponse> returnApplicationEntities = jobApplicationService.getApplicationsByParam(applicationParamForm, "en");
        assertEquals(returnApplicationEntities.size(),4);
    }
    @Ignore
    @Test
    public void getApplicationsPage(){
        given(userApi.getUserById(anyInt())).willReturn( new PersonForm("Adrian","Gortzak",new Date(2016,02,17),"addegor@hotmail.com",new RoleForm("test")));
        Collection<ApplicationEntity> collection = new ArrayList<>();
        for(int i=0; i < 10; i++) {
            collection.add(new ApplicationEntity());
        }
        given(this.mysqlApplicationDao.get10ApplicationsPage(0, "en")).willReturn(collection);
        Collection<ApplicationResponse> returnApplicationEntities  = jobApplicationService.getApplicationsPage(10, "en");
        assertEquals(returnApplicationEntities.size(),20);
    }



    @Test
    public void getApplicationsPageByBadStartId(){
        Collection<ApplicationEntity> collection = new ArrayList<>();
        for(int i=0; i < 10; i++) {
            collection.add(new ApplicationEntity());
        }
        given(this.mysqlApplicationDao.get10ApplicationsPage(-5, "en")).willReturn(collection);
        try {
            Collection<ApplicationResponse> returnApplicationEntities  = jobApplicationService.getApplicationsPage(-5, "en");
            fail("Not valid page size was accepted");
        }catch (NotValidArgumentException e){
        }
    }

    @Test
    public void changeStatusOnApplicationByIdTest(){
        String language = "en";
        ApplicationStatusForm applicationStatusForm = jobApplicationFormGenerater.getApplicationStatusForm();
        try {
            jobApplicationService.changeStatusOnApplicationById(5, applicationStatusForm, language);
        }catch (Exception e){
            fail("could not change on fake application");
        }
    }

    @Test
    public void registerJobApplicationTest(){
        String language = "en";
        try {
            jobApplicationService.registerJobApplication(jobApplicationFormGenerater.generateApplicationForm(), language);
        }catch (Exception e){
            fail("could not register new application from service");
        }
    }
}
