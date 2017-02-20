package jobApplicationApp.service;

import jobApplicationApp.dao.ApplicationDao;
import jobApplicationApp.dto.form.ApplicationForm;
import jobApplicationApp.dto.form.ApplicationParamForm;
import jobApplicationApp.dto.form.ApplicationStatusForm;
import jobApplicationApp.dto.response.ApplicationResponse;
import jobApplicationApp.entity.ApplicationEntity;
import jobApplicationApp.entity.ApplicationStatusEntity;
import jobApplicationApp.entity.CompetenceEntity;
import jobApplicationApp.exception.NotValidArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.Collection;



/**
 * Service for handling job application
 */
@Service
public class JobApplicationService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("mysql")
    private ApplicationDao applicationDao;

    @Autowired
    private UserApi userApi;

    /**
     * Get job application by id
     * @param id of application
     * @param lang
     * @return application
     */
    public ApplicationResponse getApplicationById(int id, String lang) throws NotValidArgumentException {
        validateId(id,"application id");
        ApplicationEntity applicationEntity = applicationDao.getApplicationById(id,lang);
        log.info("Application with id " + String.valueOf(id) + " was retrieved");
        return new ApplicationResponse(applicationEntity,userApi.getUserById(applicationEntity.getId()));
    }

    /**
     * Change status on application
     * @param id on application to change status on
     * @param newStatus on application
     */
    public void changeStatusOnApplicationById(int id, ApplicationStatusForm newStatus) throws NotValidArgumentException {
        validateId(id,"application id");
        applicationDao.changeApplicationStatus(id,newStatus);
        log.info("Changing status on application with id " + id +" to " + newStatus);
    }

    /**
     * Register a new job application
     * @param application to register
     * @throws NotValidArgumentException
     */
    public void registerJobApplication(ApplicationForm application) throws NotValidArgumentException {
        applicationDao.insertApplication(application);
        log.info("A new application was created by userId [" + application.getPersonId() + "]");
    }

    /**
     * Get a page with defined size of applications and also which page to retrieve
     * @param pageSize of the retrieved page
     * @param pageNmr of page to be retrieved
     * @param lang
     * @return a collection of applications
     * @throws NotValidArgumentException
     */
    public Collection<ApplicationEntity> getApplicationsPage(int pageSize, int pageNmr, String lang) throws NotValidArgumentException {
        validateId(pageNmr,"page number");
        validateId(pageSize,"page size");
        Collection<ApplicationEntity>  applications = applicationDao.getXApplicationsFrom(pageNmr*pageSize,pageSize,lang);
        log.info("applications page " + pageNmr + " with size " + pageSize + " was retrieved");
        return applications;
    }

    /**
     *Get application by parameter object to filter applications
     * @param param contains all parameters
     * @param lang
     * @return a collection of applications
     */
    public Collection<ApplicationEntity> getApplicationsByParam(ApplicationParamForm param, String lang) {
        Collection<ApplicationEntity> applicationsByParam = applicationDao.getApplicationByParam(param, lang);
        log.info("Search for application resulted in " + applicationsByParam.size() + " applications");
        return applicationsByParam;
    }

    /**
     * Validate identification
     * @param id is the identifier of the objec
     * @param idName is the name of the identifier
     * @throws NotValidArgumentException
     */
    private void validateId(int id,String idName ) throws NotValidArgumentException {
        if(id < 0){
            log.error("Request with illegal " +idName +" ->" + id);
            throw new NotValidArgumentException(idName +" " + id + " is too low, needs to be higher then 0");
        }
    }

    /**
     * Get all valid competences allowed on an application
     * @return collection of competences
     * @param lang
     */
    public Collection<CompetenceEntity> getAllValidCompetences(String lang) {
        return applicationDao.getAllValidCompetences(lang);
    }

    /**
     * Get all valid application status allowed on an application
     * @return collection of application status
     * @param lang
     */
    public Collection<ApplicationStatusEntity> getAllValidStatus(String lang) {
        return applicationDao.getAllValidStatus(lang);
    }
}
