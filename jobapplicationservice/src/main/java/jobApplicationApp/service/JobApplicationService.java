package jobApplicationApp.service;

import jobApplicationApp.dao.ApplicationDao;
import jobApplicationApp.dao.MysqlApplicationDao;
import jobApplicationApp.dto.ApplicationParam;
import jobApplicationApp.entity.ApplicationEntity;
import jobApplicationApp.entity.ApplicationStatusEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class JobApplicationService {

    ApplicationDao applicationDao = new MysqlApplicationDao();


    public ApplicationEntity getApplicationById(int id){
       if(validId(id))
        return applicationDao.getById(id);
       else
           return null;//todo error handling
    }


    public void changeStatusOnApplicationById(int id, ApplicationStatusEntity status) {
        if(validId(id))
        applicationDao.changeStatus(id,status);
    }

    public void changeStatusOnApplication(ApplicationEntity application, ApplicationStatusEntity status) {
        changeStatusOnApplicationById(application.getApplicationId(), status);
    }

    public void registerJobApplication(ApplicationEntity application) {
        applicationDao.insert(application);
    }

    public Collection<ApplicationEntity> getAHundredApplicationsFrom(int index) {
        if(validId(index))
           return applicationDao.getAHundredApplicationsFrom(index);
        else return null; //todo error handling
    }

    public Collection<ApplicationEntity> getApplicationByParam(ApplicationParam param) {
        return null; //todo error handling
    }

    private boolean validId(int id ){
        if(id < 0){
            return false;
        }
        else if(applicationDao.exists(id))
        {
            return false;
        }else {
            return true;
        }
    }
}
