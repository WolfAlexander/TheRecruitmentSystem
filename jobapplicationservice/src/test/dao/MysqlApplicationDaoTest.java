package dao;

import jobApplicationApp.JobApplicationLauncher;
import jobApplicationApp.dao.ApplicationDao;
import jobApplicationApp.dao.MysqlApplicationDao;
import jobApplicationApp.dao.repository.*;
import jobApplicationApp.entity.*;
import org.hibernate.cfg.Environment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobApplicationLauncher.class)
@ActiveProfiles("test")
public class MysqlApplicationDaoTest  {

    @Test
    public void fuckOfTest(){
        fail("Noooo..");
    }

    private final Logger log = LoggerFactory.getLogger(this.getClass());
/*
    @Autowired
    @Qualifier("mysql")
    private ApplicationDao applicationDao;
*/

/*
    private int applicationId;
    private Date dateOfBirth = null;
    private Date registrationDate = null;

    @Before
    public void setup(){

        try {
            dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse("1995-02-14");
            registrationDate = new SimpleDateFormat("yyyy-MM-dd").parse("2016-02-17");
        } catch (ParseException e) {
            fail("Could not create dateOfBirth from string");
        }
        ApplicationEntity application = this.entityManager.persist(
                new ApplicationEntity(new PersonEntity("Henrik","Gustavsson",dateOfBirth,"henrik.gustavsson@hotmail.com",new RoleEntity("Recruiter")),
                        new Date(),
                        new ApplicationStatusEntity("Pending"),
                        new AvailabilityEntity(),
                        new ArrayList<CompetenceProfileEntity>()));
        applicationId=application.getId();
        log.info("the id is " +applicationId);
    }


    @Test
    public void contextLoads(){
        assertThat(applicationRepository).isNotNull();
        assertThat(statusRepository).isNotNull();
        assertThat(personRepository).isNotNull();
        assertThat(competenceProfileRepository).isNotNull();
        assertThat(availableRepository).isNotNull();
        //assertThat(applicationDao).isNotNull();
    }

    @Test
    public void getApplicationById() {
        ApplicationEntity requestedApplication = applicationRepository.findOne(applicationId);
        //Person
        assertThat(requestedApplication.getPerson().getFirstname()).isEqualTo("Henrik");
        assertThat(requestedApplication.getPerson().getLastname()).isEqualTo("Gustavsson");
        assertThat(requestedApplication.getPerson().getDateOfBirth()).isEqualTo(dateOfBirth);
        assertThat(requestedApplication.getPerson().getEmail()).isEqualTo("henrik.gustavsson@hotmail.com");
        assertThat(requestedApplication.getPerson().getRole().getName()).isEqualTo("Recruiter");
        //Registation date
//        assertThat(requestedApplication.getDateOfRegistration()).isEqualTo(registrationDate); todo
        //Application status
        assertThat(requestedApplication.getStatus().getName()).isEqualTo("Pending");
        //todo Availability
        //todo Competenceprofile

    }

    @Test
    public void changeApplicationStatus() {
        ApplicationEntity application =applicationRepository.findOne(applicationId);
                application.changeStatus(new ApplicationStatusEntity("ACCEPTED"));
        ApplicationEntity newApplication = applicationRepository.findOne(applicationId);
        assertThat(newApplication.getStatus().getName()).isEqualTo("ACCEPTED");
    }

    @Test
    public void applicationExists() {
        assertThat(applicationRepository.exists(applicationId)).isTrue();
        assertThat(applicationRepository.exists(applicationId+1)).isFalse();
    }

    @Test
    public void insertApplication() {

    }

    @Test
    public void getXApplicationsFrom() {
        assertThat(applicationRepository.getXApplicationsFrom(0,100).size()).isEqualTo(1);
        assertThat(applicationRepository.getXApplicationsFrom(100,100).size()).isEqualTo(0);

    }

    @Test
    public void getAllApplication() {
        assertThat(applicationRepository.findAll()).isNotNull();
    }
*/
}
