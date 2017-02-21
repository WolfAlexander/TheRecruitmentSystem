package dao;

import jobApplicationApp.JobApplicationLauncher;
import jobApplicationApp.dao.MysqlApplicationDao;
import jobApplicationApp.dao.repository.*;
import jobApplicationApp.dto.form.ApplicationForm;
import jobApplicationApp.dto.form.ApplicationParamForm;
import jobApplicationApp.dto.form.ApplicationStatusForm;
import jobApplicationApp.entity.ApplicationEntity;
import jobApplicationApp.entity.ApplicationStatusEntity;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import utils.JobApplicationEntityGenerater;
import utils.JobApplicationFormGenerater;
import static junit.framework.TestCase.fail;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = JobApplicationLauncher.class)
public class MysqlApplicationDao_mockTets {

    private JobApplicationEntityGenerater jobApplicationEntityGenerater = new JobApplicationEntityGenerater();
    private JobApplicationFormGenerater jobApplicationFormGenerater = new JobApplicationFormGenerater();
    @MockBean
    private ApplicationStatusRepository statusRepository;

    @MockBean
    private ApplicationRepository applicationRepository;

    @MockBean
    private AvailableRepository availableRepository;

    @MockBean
    private CompetenceRepository competenceRepository;

    @MockBean
    private CompetenceProfileRepository competenceProfileRepository;

    @Autowired
    private MysqlApplicationDao mysqlApplicationDao;

    @Test
    public void getApplicationByNameParam(){
        assertThat(mysqlApplicationDao.getApplicationByParam(new ApplicationParamForm("henrik",null,null), "en").size()).isEqualTo(0);
    }

    @Test
    public void getApplicationByAvailability(){
        assertThat(mysqlApplicationDao.getApplicationByParam(new ApplicationParamForm(null,jobApplicationFormGenerater.getAvailabilityForm(),null), "en").size()).isEqualTo(0);
    }

    @Test
    public void insertApplicationTest(){
        String language = "en";
        ApplicationForm applicationForm = jobApplicationFormGenerater.generateApplicationForm();
        given(availableRepository.findByFromDateAndToDate(applicationForm.getAvailableForWork().getFromDate(),applicationForm.getAvailableForWork().getToDate())).willReturn(jobApplicationEntityGenerater.generateAvailabilityEntity());
        given(applicationRepository.save(jobApplicationEntityGenerater.generateApplicationEntity())).willReturn(null);

    //    competenceRepository.findByName(competenceProfileEntity.getName());
     //   competenceProfileRepository.save(c));

        try {
            mysqlApplicationDao.insertApplication(jobApplicationFormGenerater.generateApplicationForm(), language);
            fail("Could not insert the new application");
        }catch (Exception e){}

    }

}
