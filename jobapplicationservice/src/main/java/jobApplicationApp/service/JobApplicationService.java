package jobApplicationApp.service;

import jobApplicationApp.dao.ApplicationDao;
import jobApplicationApp.dto.ApplicationParam;
import jobApplicationApp.entity.ApplicationEntity;
import jobApplicationApp.entity.CompetenceEntity;
import jobApplicationApp.entity.CompetenceProfileEntity;
import jobApplicationApp.entity.ApplicationStatusEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.Collection;
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
    public ApplicationEntity getApplicationById(int id){
        validId(id);
        ApplicationEntity applicationEntity = applicationDao.getApplicationById(id);
        log.info("Application with id " + String.valueOf(id) + " was retrieved from db");
        return applicationEntity;
    }

    /**
     * Change status on application
     * @param id on application we want to change status on
     * @param newStatus on application
     */
    public void changeStatusOnApplicationById(int id, ApplicationStatusEntity newStatus) {
        validId(id);
        applicationDao.changeApplicationStatus(id,newStatus);
        log.info("Changing status on application with id " + id +" to " + newStatus.getName());
    }

    /**
     * Change status on application
     * @param application to change status on
     */
    public void changeStatusOnApplication(ApplicationEntity application) {
        changeStatusOnApplicationById(application.getId(), application.getStatus());
    }

    public void registerJobApplication(ApplicationEntity application) {
        applicationDao.insertApplication(application);
        log.info("A new application was created by userId [" + application.getPerson().getId() + "]");
    }

    public Collection<ApplicationEntity> getApplicationPage(int id) {
        validId(id*100);
        Collection<ApplicationEntity>  applications = applicationDao.getAHundredApplicationsFrom(id*100);
        log.info("applications page " + id + " was retrieved");
        return applications;
    }

    public Collection<ApplicationEntity> getApplicationByParam(ApplicationParam param) {
        Map<Integer, ApplicationEntity> allApplication = applicationDao.getAllApplication();

        if(param.getAvailableFrom() != null){
            allApplication.forEach((k,v)->{
                if(!(param.getAvailableFrom().before(v.getAvailableForWork().getFromDate()) && (v.getAvailableForWork().getToDate() == null || v.getAvailableForWork().getToDate().after(param.getAvailableFrom())))){
                    allApplication.remove(v.getId());
                }
            });
        }

        if(param.getAvailableTo() != null){
            allApplication.forEach((k,v)->{
                if(v.getAvailableForWork().getToDate().after(param.getAvailableTo()) && (v.getAvailableForWork().getFromDate().before(param.getAvailableTo()) || v.getAvailableForWork().getFromDate() == null)){
                    allApplication.remove(v.getId());
                }
            });
        }

        if(param.getCompetences() != null){
            allApplication.forEach((k,v)->{
                Collection<CompetenceEntity> competences = param.getCompetences();
                for(CompetenceProfileEntity c : v.getCompetenceProfile()){
                    if(competences.contains(c)){
                        competences.remove(c);
                    }
                }
                if(competences.size() > 0){
                    allApplication.remove(v.getId());
                }
            });

        }
        if(param.getName() != null){
            //Todo what is this!?
        }
        log.info(allApplication.size() + " application was found in search with param");
        return allApplication.values();
    }

    private void validId(int id ){
        if(id < 0){
         //   return false;
        }
        else if(applicationDao.applicationExists(id))
        {
         //   return false;
        }else {

        }
    }
}
