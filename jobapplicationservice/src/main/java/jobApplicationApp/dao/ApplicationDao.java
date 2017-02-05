package jobApplicationApp.dao;


import jobApplicationApp.entity.ApplicationEntity;
import jobApplicationApp.entity.ApplicationStatusEntity;

import java.util.Collection;

public interface ApplicationDao {

    ApplicationEntity getById(int id);
    void changeStatus(int applicationId, ApplicationStatusEntity status);
    boolean exists(int id);
    void insert(ApplicationEntity application);
    Collection<ApplicationEntity> getAHundredApplicationsFrom(int index);
}
