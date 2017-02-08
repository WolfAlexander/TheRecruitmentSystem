package jobApplicationApp.service;

import jobApplicationApp.dao.ApplicationDao;
import jobApplicationApp.dto.ApplicationForm;
import jobApplicationApp.dto.ApplicationParamForm;
import jobApplicationApp.dto.AvailabilityForm;
import jobApplicationApp.dto.CompetenceForm;
import jobApplicationApp.entity.ApplicationEntity;
import jobApplicationApp.entity.CompetenceProfileEntity;
import jobApplicationApp.exception.NotValidArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.IllegalFormatCodePointException;
import java.util.Map;

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
        log.info("Application with id " + String.valueOf(id) + " was retrieved from db");
        return applicationEntity;
    }

    /**
     * Change status on application
     * @param id on application we want to change status on
     * @param newStatus on application
     */
    public void changeStatusOnApplicationById(int id, String newStatus) throws NotValidArgumentException {
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
        log.info("applications page " + pageNmr + " with size " + pageSize + "was retrieved");
        return applications;
    }

    public Collection<ApplicationEntity> getApplicationsByParam(ApplicationParamForm param) {
        Map<Integer, ApplicationEntity> allApplication = applicationDao.getAllApplication();

        if(param.getAvailability().getFromDate() != null){
         allApplication = removeApplicationsThatIsNotAvailableAtRequiredStartDate(allApplication,param.getAvailability());
        }

        if(param.getAvailability().getToDate() != null){
         allApplication = removeApplicationsThatIsNotAvailableToRequiredEndDate(allApplication,param.getAvailability());
        }

        if(param.getCompetences() != null){
            allApplication = removeApplicationThatDoesntContainCompetence(allApplication,param.getCompetences());
        }

        if(param.getName() != null){
            //Todo what is this!?
        }
        log.info(allApplication.size() + " application was found in search with param");
        return allApplication.values();
    }

    private Map<Integer, ApplicationEntity> removeApplicationsThatIsNotAvailableAtRequiredStartDate(Map<Integer, ApplicationEntity> allApplication, AvailabilityForm availability){
        allApplication.forEach((k,v)->{
            if(!(availability.getFromDate().before(v.getAvailableForWork().getFromDate()) && (v.getAvailableForWork().getToDate() == null || v.getAvailableForWork().getToDate().after(availability.getFromDate())))){
                allApplication.remove(v.getId());
            }
        });
        return allApplication;
    }

    private Map<Integer, ApplicationEntity> removeApplicationsThatIsNotAvailableToRequiredEndDate(Map<Integer, ApplicationEntity> allApplication, AvailabilityForm availability) {
        allApplication.forEach((k,v)->{
            if(v.getAvailableForWork().getToDate().after(availability.getToDate()) && (v.getAvailableForWork().getFromDate().before(availability.getToDate()) || v.getAvailableForWork().getFromDate() == null)){
                allApplication.remove(v.getId());
            }
        });
        return allApplication;
    }

    private Map<Integer, ApplicationEntity> removeApplicationThatDoesntContainCompetence(Map<Integer, ApplicationEntity> allApplication, Collection<CompetenceForm> requiredCompetences){
        allApplication.forEach((k,v)->{
            Collection<CompetenceForm> competences = requiredCompetences;
            for(CompetenceProfileEntity c : v.getCompetenceProfile()){
                if(competences.contains(c)){
                    competences.remove(c);
                }
            }
            if(competences.size() > 0){
                allApplication.remove(v.getId());
            }
        });
        return allApplication;
    }


    private void validateId(int id ) throws NotValidArgumentException {
        if(id < 0){
            log.error("Request with illegal id ->" + id);
            throw new NotValidArgumentException("Id " + id + " is too low");
        }
    }
}
