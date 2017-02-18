package jobApplicationApp.dao;


import jobApplicationApp.dto.form.ApplicationForm;
import jobApplicationApp.dto.form.ApplicationParamForm;
import jobApplicationApp.dto.form.ApplicationStatusForm;
import jobApplicationApp.entity.ApplicationEntity;
import jobApplicationApp.entity.ApplicationStatusEntity;
import jobApplicationApp.entity.CompetenceEntity;
import jobApplicationApp.exception.NotValidArgumentException;

import java.util.Collection;

public interface ApplicationDao {
    ApplicationEntity getApplicationById(int id, String language);
    void changeApplicationStatus(int applicationId, ApplicationStatusForm status) throws NotValidArgumentException;
    void insertApplication(ApplicationForm application) throws NotValidArgumentException;
    Collection<ApplicationEntity> getXApplicationsFrom(int startId, int numberOfApplication, String lang);
    Collection<ApplicationEntity> getApplicationByParam(ApplicationParamForm param, String lang);
    Collection<CompetenceEntity> getAllValidCompetences(String lang);
    Collection <ApplicationStatusEntity> getAllValidStatus(String lang);
}
