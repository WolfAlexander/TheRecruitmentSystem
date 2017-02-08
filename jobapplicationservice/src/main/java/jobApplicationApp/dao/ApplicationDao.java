package jobApplicationApp.dao;


import jobApplicationApp.dto.form.ApplicationForm;
import jobApplicationApp.dto.form.ApplicationParamForm;
import jobApplicationApp.entity.ApplicationEntity;
import jobApplicationApp.exception.NotValidArgumentException;

import java.util.Collection;
import java.util.Map;

public interface ApplicationDao {

    ApplicationEntity getApplicationById(int id);
    void changeApplicationStatus(int applicationId, String status);
    void insertApplication(ApplicationForm application) throws NotValidArgumentException;
    Collection<ApplicationEntity> getXApplicationsFrom(int startId, int numberOfApplication);
    Collection<ApplicationEntity> getApplicationByParam(ApplicationParamForm param);
}
