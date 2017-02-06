package jobApplicationApp.dao;


import jobApplicationApp.entity.ApplicationEntity;
import jobApplicationApp.entity.ApplicationStatusEntity;

import java.util.Collection;
import java.util.Map;

public interface ApplicationDao {

    ApplicationEntity getApplicationById(int id);
    void changeApplicationStatus(int applicationId, ApplicationStatusEntity status);
    boolean applicationExists(int id);
    void insertApplication(ApplicationEntity application);
    Collection<ApplicationEntity> getAHundredApplicationsFrom(int index);
    Map<Integer, ApplicationEntity> getAllApplication();
}
