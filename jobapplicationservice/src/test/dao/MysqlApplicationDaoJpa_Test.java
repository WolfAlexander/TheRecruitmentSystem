package dao;

import jobApplicationApp.JobApplicationLauncher;
import jobApplicationApp.dao.repository.*;
import jobApplicationApp.entity.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import utils.JobApplicationEntityGenerater;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static junit.framework.TestCase.fail;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobApplicationLauncher.class)
@ActiveProfiles("test")
@DataJpaTest
public class MysqlApplicationDaoJpa_Test {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //private ApplicationDao applicationDao = new MysqlApplicationDao();
    @Autowired
    private TestEntityManager entityManager;
    JobApplicationEntityGenerater jobApplicationEntityGenerater = new JobApplicationEntityGenerater();

    @Autowired private ApplicationRepository applicationRepository;
    @Autowired private ApplicationStatusRepository statusRepository;
    @Autowired private CompetenceProfileRepository competenceProfileRepository;
    @Autowired private AvailableRepository availableRepository;


    private int applicationId;
    private Date dateOfBirth = null;
    private Date registrationDate = null;

    @Before
    public void setup(){
        try {
            this.dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse("1995-02-14");
            this.registrationDate = new SimpleDateFormat("yyyy-MM-dd").parse("2016-02-17");
        } catch (ParseException e) {
            fail("Could not create dateOfBirth from string");
        }
        entityManager.persist(new ApplicationStatusEntity("PENDING"));


        //Create Application
        ApplicationEntity application = this.entityManager.persist(jobApplicationEntityGenerater.generateApplicationEntity());

        this.applicationId=application.getId();
    }

    @Test
    public void contextLoads(){
        assertThat(applicationRepository).isNotNull();
        assertThat(statusRepository).isNotNull();
        assertThat(competenceProfileRepository).isNotNull();
        assertThat(availableRepository).isNotNull();
    }

    @Test
    public void getApplicationById() {
        ApplicationEntity requestedApplication = applicationRepository.findOne(applicationId);
        assertThat(requestedApplication).isNotNull();
        //todo Availability
        //todo Competenceprofile

    }

    @Test
    public void getApplicationStatus(){
        ApplicationEntity requestedApplication = applicationRepository.findOne(applicationId);
        assertThat(requestedApplication.getStatus().getName()).isEqualTo("PENDING");
    }


    @Test
    public void changeApplicationStatus() {
        ApplicationEntity application =applicationRepository.findOne(applicationId);
                application.changeStatus(new ApplicationStatusEntity("ACCEPTED"));
        ApplicationEntity newApplication = applicationRepository.findOne(applicationId);
        assertThat(newApplication.getStatus().getName()).isEqualTo("ACCEPTED");
    }



    @Test
    public void insertApplication() {
        ApplicationEntity a = jobApplicationEntityGenerater.generateApplicationEntity();
        ApplicationEntity newApplication = applicationRepository.save(a);
        assertThat(applicationRepository.findOne(newApplication.getId())).isNotNull();
        applicationRepository.delete(a);
    }

    @Ignore
    @Test
    public void getXApplicationsFrom() {
        assertThat(applicationRepository.getXApplicationsFrom(0,100).size()).isEqualTo(1);
        assertThat(applicationRepository.getXApplicationsFrom(100,100).size()).isEqualTo(0);
    }


}
