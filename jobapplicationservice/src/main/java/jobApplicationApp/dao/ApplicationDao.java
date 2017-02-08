package jobApplicationApp.dao;


import jobApplicationApp.dto.ApplicationForm;
import jobApplicationApp.entity.ApplicationEntity;
import jobApplicationApp.exception.NotValidArgumentException;

import java.util.Collection;
import java.util.Map;

public interface ApplicationDao {

    ApplicationEntity getApplicationById(int id);
    void changeApplicationStatus(int applicationId, String status);
    boolean applicationExists(int id);
    void insertApplication(ApplicationForm application) throws NotValidArgumentException;
    Collection<ApplicationEntity> getXApplicationsFrom(int startId, int numberOfApplication);
    Map<Integer, ApplicationEntity> getAllApplication();
}
