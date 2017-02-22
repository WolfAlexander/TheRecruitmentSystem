package dao;

import jobApplicationApp.JobApplicationLauncher;
import jobApplicationApp.dao.MysqlApplicationDao;
import jobApplicationApp.dao.repository.*;
import jobApplicationApp.dao.repository.localized.LanguageRepository;
import jobApplicationApp.dao.repository.localized.LocalizedCompetenceRepository;
import jobApplicationApp.dao.repository.localized.LocalizedStatusRepository;
import jobApplicationApp.dto.form.ApplicationForm;
import jobApplicationApp.dto.form.ApplicationParamForm;
import jobApplicationApp.dto.form.ApplicationStatusForm;
import jobApplicationApp.entity.ApplicationEntity;
import jobApplicationApp.entity.ApplicationStatusEntity;
import jobApplicationApp.entity.localized.LanguageEntity;
import jobApplicationApp.entity.localized.LocalizedCompetence;
import jobApplicationApp.entity.localized.LocalizedStatus;
import jobApplicationApp.exception.NoMatchException;
import jobApplicationApp.service.UserApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import utils.JobApplicationEntityGenerator;
import utils.JobApplicationFormGenerater;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;


@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = JobApplicationLauncher.class)
public class MysqlApplicationDaoTest {

    private JobApplicationEntityGenerator jobApplicationEntityGenerator = new JobApplicationEntityGenerator();
    private JobApplicationFormGenerater jobApplicationFormGenerater = new JobApplicationFormGenerater();
    @MockBean private ApplicationStatusRepository statusRepository;
    @MockBean private ApplicationRepository applicationRepository;
    @MockBean private AvailableRepository availableRepository;
    @MockBean private CompetenceRepository competenceRepository;
    @MockBean private CompetenceProfileRepository competenceProfileRepository;
    @MockBean private LocalizedStatusRepository localizedStatusRepository;
    @MockBean private LanguageRepository languageRepository;
    @MockBean private LocalizedCompetenceRepository localizedCompetenceRepository;
    @MockBean private UserApi userApi;
    @Autowired
    private jobApplicationApp.dao.MysqlApplicationDao mysqlApplicationDao;




    @Test
    public void getApplicationById(){
        ApplicationEntity ae = jobApplicationEntityGenerator.generateApplicationEntity();
        given(applicationRepository.findOne(anyInt())).willReturn(ae);
        mockTranslation();
        assertEquals(ae.getCompetenceProfile().size(),mysqlApplicationDao.getApplicationById(1,"en").getCompetenceProfile().size());
        assertEquals(ae.getStatus(),mysqlApplicationDao.getApplicationById(1,"en").getStatus());
    }

    @Test
    public void getApplicationByBadId(){
        given(applicationRepository.findOne(1)).willReturn(null);
        try {
            mysqlApplicationDao.getApplicationById(1,"en");
            fail("should throw exception");
        }catch (NoMatchException e){ }
    }

    @Test
    public void getAllValidStatus(){
        mockTranslation();
        given(statusRepository.findAll()).willReturn(jobApplicationEntityGenerator.generateListOfApplicationStatus());
        try{
            mysqlApplicationDao.getAllValidStatus("en");
        }catch (Exception e){
            fail("should'nt throw exceptions");
        }
    }

    @Test
    public void getAllValidCompetences(){
        mockTranslation();
        given(competenceRepository.findAll()).willReturn(jobApplicationEntityGenerator.generateListOfCompetences());
        try{
            mysqlApplicationDao.getAllValidCompetences("en");
        }catch (Exception e){
            fail("should'nt throw exceptions");
        }
    }

    private void mockTranslation(){
        given(languageRepository.findByName("en")).willReturn(new LanguageEntity(1,"en"));
        given(localizedStatusRepository.getByLanguageIdAndStatusId(anyInt(),anyInt())).willReturn(new LocalizedStatus(1,1,"testStatus"));
        given(localizedCompetenceRepository.getByLanguageIdAndCompetenceId(anyInt(),anyInt())).willReturn(new LocalizedCompetence(1,1,"running"));
    }

    @Test
    public void changeApplicationStatus(){
         mockTranslation();
         given(localizedStatusRepository.getByLanguageIdAndTranslation(1,jobApplicationFormGenerater.getApplicationStatusForm().getName())).willReturn(jobApplicationEntityGenerator.generateLocalizedStatus());
         given(statusRepository.findOne(anyInt())).willReturn(jobApplicationEntityGenerator.generateApplicationStatusEntity());
         given(applicationRepository.findOne(0)).willReturn(jobApplicationEntityGenerator.generateApplicationEntity());
         mysqlApplicationDao.changeApplicationStatus(0,jobApplicationFormGenerater.getApplicationStatusForm(),"en");
    }

    @Test
    public void changeApplicationStatusWithBadStatus(){
        try {
            mockTranslation();
            given(localizedStatusRepository.getByLanguageIdAndTranslation(1, jobApplicationFormGenerater.getApplicationStatusForm().getName())).willReturn(null);
            given(statusRepository.findOne(anyInt())).willReturn(jobApplicationEntityGenerator.generateApplicationStatusEntity());
            given(applicationRepository.findOne(0)).willReturn(jobApplicationEntityGenerator.generateApplicationEntity());
            mysqlApplicationDao.changeApplicationStatus(0, jobApplicationFormGenerater.getApplicationStatusForm(), "en");
            fail("Should throw exceptions");
        }catch (Exception e){
        }
    }


    //todo
    @Test
    public void getApplicationByNameParam(){
        mockTranslation();
        ArrayList<Integer> listOfIds = new ArrayList<>();
        listOfIds.add(1);
        given(userApi.getIdOfUsersWithName(anyString())).willReturn(listOfIds);
        given(applicationRepository.findAll()).willReturn(jobApplicationEntityGenerator.generateListOfApplicationWithTheSize(2));
        assertThat(mysqlApplicationDao.getApplicationByParam(new ApplicationParamForm("henrik",null,null), "en").size()).isEqualTo(2);
    }

    @Test
    public void getXApplicationFrom(){
        mockTranslation();
        given(applicationRepository.getXApplicationsFrom(0,20)).willReturn(jobApplicationEntityGenerator.generateListOfApplicationWithTheSize(10));
        assertEquals(10, mysqlApplicationDao.getXApplicationsFrom(0,20,"en").size());
    }



    //todo should throw NotValidArgumentException
    @Test
    public void badLanguage(){
        try {
            given(languageRepository.findByName("en")).willReturn(null);
            given(localizedStatusRepository.getByLanguageIdAndStatusId(anyInt(),anyInt())).willReturn(new LocalizedStatus(1,1,"testStatus"));
            given(localizedCompetenceRepository.getByLanguageIdAndCompetenceId(anyInt(),anyInt())).willReturn(new LocalizedCompetence(1,1,"testStatus"));
            given(competenceRepository.findAll()).willReturn(jobApplicationEntityGenerator.generateListOfCompetences());
            mysqlApplicationDao.getAllValidCompetences("en");
            fail("should throw exception");
        }catch (Exception e){
        }
    }

    //todo
    @Test
    public void getApplicationByAvailability(){
        assertThat(mysqlApplicationDao.getApplicationByParam(new ApplicationParamForm(null,jobApplicationFormGenerater.getAvailabilityForm(),null), "en").size()).isEqualTo(0);
    }

    @Test
    public void insertApplication(){
        String language = "en";
        given(languageRepository.findByName("en")).willReturn(new LanguageEntity(1,"en"));
        ApplicationForm applicationForm = jobApplicationFormGenerater.generateApplicationForm();
        given(availableRepository.findByFromDateAndToDate(applicationForm.getAvailableForWork().getFromDate(),applicationForm.getAvailableForWork().getToDate())).willReturn(jobApplicationEntityGenerator.generateAvailabilityEntity());
        given(applicationRepository.save(jobApplicationEntityGenerator.generateApplicationEntity())).willReturn(null);
        given(localizedCompetenceRepository.getByLanguageIdAndTranslation(anyInt(),anyString())).willReturn(jobApplicationEntityGenerator.generateLocalizedCompetence());
        try {
            mysqlApplicationDao.insertApplication(jobApplicationFormGenerater.generateApplicationForm(), language);
        }catch (Exception e){
            e.printStackTrace();
            fail("Could not insert the new application");
        }

    }

    @Test
    public void getListOfIntersectionBetweenApplicationList(){
        mockTranslation();
        ArrayList<Integer> listOfIds = new ArrayList<>();
        listOfIds.add(1);
        given(applicationRepository.getApplicationsThatCanWorkFrom(any())).willReturn(jobApplicationEntityGenerator.generateListOfApplicationWithTheSize(2));
        given(applicationRepository.getApplicationsThatCanWorkTo(any())).willReturn(jobApplicationEntityGenerator.generateListOfApplicationWithTheSize(2));
        given(userApi.getIdOfUsersWithName(anyString())).willReturn(listOfIds);
        given(applicationRepository.findAll()).willReturn(jobApplicationEntityGenerator.generateListOfApplicationWithTheSize(2));
       assertEquals(2,mysqlApplicationDao.getApplicationByParam(new ApplicationParamForm("sbrm",jobApplicationFormGenerater.getAvailabilityForm(),null),"en").size());
    }


    @Test
    public void filterByCompetenceWithoutResult(){
            mockTranslation();
            given(localizedCompetenceRepository.getByLanguageIdAndTranslation(anyInt(), anyString())).willReturn(jobApplicationEntityGenerator.generateLocalizedCompetence());
            assertEquals(0,mysqlApplicationDao.getApplicationByParam(new ApplicationParamForm(null, null, jobApplicationFormGenerater.generateListCompetenceFormWithSize(2)), "en").size());
    }


    @Test
    public void insertApplicationWithBadCompetence(){
        given(languageRepository.findByName("en")).willReturn(new LanguageEntity(1,"en"));
        ApplicationForm applicationForm = jobApplicationFormGenerater.generateApplicationForm();
        given(availableRepository.findByFromDateAndToDate(applicationForm.getAvailableForWork().getFromDate(),applicationForm.getAvailableForWork().getToDate())).willReturn(jobApplicationEntityGenerator.generateAvailabilityEntity());
        given(applicationRepository.save(jobApplicationEntityGenerator.generateApplicationEntity())).willReturn(null);
        given(localizedCompetenceRepository.getByLanguageIdAndTranslation(anyInt(),anyString())).willReturn(null);
        try {
            mysqlApplicationDao.insertApplication(jobApplicationFormGenerater.generateApplicationForm(), "en");
            fail("Could not insert the new application");
        }catch (Exception e){
            assertEquals("Not valid name on competence",e.getMessage());
        }

    }
}
