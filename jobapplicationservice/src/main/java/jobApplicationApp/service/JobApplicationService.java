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

import java.util.ArrayList;
import java.util.Collection;


/**
 * Service for handling job application
 *
 * @author Adrian Gortzak gortzak@kth.se
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
     * Get a job application by id
     * @param id of application
     * @param language of application
     * @return an application
     */
    public ApplicationResponse getApplicationById(int id, String language) throws NotValidArgumentException {
        validateId(id,"application id");
        ApplicationEntity applicationEntity = applicationDao.getApplicationById(id,language);
        log.info("Application with id " + String.valueOf(id) + " was retrieved");
        return convertToApplicationResponse(applicationEntity);
    }

    /**
     * Change status on the application
     * @param id of application to change status on
     * @param newStatus of application
     * @param language of application
     * @throws NotValidArgumentException
     */
    public void changeStatusOnApplicationById(int id, ApplicationStatusForm newStatus, String language) throws NotValidArgumentException {
        validateId(id,"application id");
        applicationDao.changeApplicationStatus(id,newStatus,language);
        log.info("Changing status on application with id " + id +" to " + newStatus);
    }

    /**
     * Register a new job application
     * @param application to register
     * @param language of application
     * @throws NotValidArgumentException
     */
    public void registerJobApplication(ApplicationForm application, String language) throws NotValidArgumentException {
        applicationDao.insertApplication(application, language);
        log.info("A new application was created by userId [" + application.getPersonId() + "]");
    }

    /**
     * Get a page with specific amount of applications and also which page to retrieve
     * @param pageNmr of page to be retrieved
     * @param language of application
     * @return a collection of applications
     * @throws NotValidArgumentException
     */
    public Collection<ApplicationResponse> getApplicationsPage( int pageNmr, String language) throws NotValidArgumentException {
        validateId(pageNmr,"page number");

        Collection<ApplicationEntity>  applications = applicationDao.get10ApplicationsPage(pageNmr,language);
        log.info("applications page " + pageNmr + " was retrieved");
        return convertListToApplicationResponse(applications);
    }

    /**
     *Get application by parameter object to filter applications
     * @param param contains all parameters
     * @param language of application's parameters
     * @return a collection of applications
     */
    public Collection<ApplicationResponse> getApplicationsByParam(ApplicationParamForm param, String language) {
        Collection<ApplicationEntity> applicationsByParam = applicationDao.getApplicationByParam(param, language);
        log.info("Search for application resulted in " + applicationsByParam.size() + " applications");
        return convertListToApplicationResponse(applicationsByParam);
    }

    private ApplicationResponse convertToApplicationResponse(ApplicationEntity application){
            return new ApplicationResponse(application, userApi.getUserById(application.getPersonId()));
    }
    private Collection<ApplicationResponse> convertListToApplicationResponse(Collection<ApplicationEntity> applications){
        ArrayList<ApplicationResponse> responses = new ArrayList<>();
        applications.forEach((a)->{
            responses.add(convertToApplicationResponse(a));
        });
        return responses;
    }



    /**
     * Validate identification
     * @param id is the identifier of the object
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
     * @param language of status
     */
    public Collection<CompetenceEntity> getAllValidCompetences(String language) {
        return applicationDao.getAllValidCompetences(language);
    }

    /**
     * Get all valid application status allowed on an application
     * @return collection of application status
     * @param language of status
     */
    public Collection<ApplicationStatusEntity> getAllValidStatus(String language) {
        return applicationDao.getAllValidStatus(language);
    }
}
