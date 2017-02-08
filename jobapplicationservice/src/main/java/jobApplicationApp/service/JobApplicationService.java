package jobApplicationApp.service;

import jobApplicationApp.dao.ApplicationDao;
import jobApplicationApp.dto.form.ApplicationForm;
import jobApplicationApp.dto.form.ApplicationParamForm;
import jobApplicationApp.dto.form.ApplicationStatusForm;
import jobApplicationApp.entity.ApplicationEntity;
import jobApplicationApp.entity.ApplicationStatusEntity;
import jobApplicationApp.entity.CompetenceEntity;
import jobApplicationApp.exception.NotValidArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import java.util.Collection;



/**
 * Service for handling with job application
 */
@Service
public class JobApplicationService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("mysql")
    private ApplicationDao applicationDao;

    /**
     * Get job application by id
     * @param id of application
     * @return application
     */
    public ApplicationEntity getApplicationById(int id) throws NotValidArgumentException {
        validateId(id);
        ApplicationEntity applicationEntity = applicationDao.getApplicationById(id);
        log.info("Application with id " + String.valueOf(id) + " was retrieved");
        return applicationEntity;
    }

    /**
     * Change status on application
     * @param id on application we want to change status on
     * @param newStatus on application
     */
    public void changeStatusOnApplicationById(int id, ApplicationStatusForm newStatus) throws NotValidArgumentException {
        validateId(id);
        applicationDao.changeApplicationStatus(id,newStatus);
        log.info("Changing status on application with id " + id +" to " + newStatus);
    }

    public void registerJobApplication(ApplicationForm application) throws NotValidArgumentException {
        applicationDao.insertApplication(application);
        log.info("A new application was created by userId [" + application.getPersonId() + "]");
    }

    public Collection<ApplicationEntity> getApplicationsPage(int pageSize ,int pageNmr) throws NotValidArgumentException {
        Collection<ApplicationEntity>  applications = applicationDao.getXApplicationsFrom(pageNmr*pageSize,pageSize);
        log.info("applications page " + pageNmr + " with size " + pageSize + " was retrieved");
        return applications;
    }

    public Collection<ApplicationEntity> getApplicationsByParam(ApplicationParamForm param) {
        Collection<ApplicationEntity> applicationsByParam = applicationDao.getApplicationByParam(param);
        log.info("Search for application resulted in " + applicationsByParam.size() + " applications");
        return applicationsByParam;
    }


    private void validateId(int id ) throws NotValidArgumentException {
        if(id < 0){
            log.error("Request with illegal id ->" + id);
            throw new NotValidArgumentException("Id " + id + " is too low");
        }
    }

    public Collection<CompetenceEntity> getAllValidCompetences() {
        return applicationDao.getAllValidCompetences();
    }

    public Collection<ApplicationStatusEntity> getAllValidStatus() {
        return applicationDao.getAllValidStatus();
    }
}
