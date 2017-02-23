package jobApplicationApp.dao;


import jobApplicationApp.dto.form.ApplicationForm;
import jobApplicationApp.dto.form.ApplicationParamForm;
import jobApplicationApp.dto.form.ApplicationStatusForm;
import jobApplicationApp.entity.ApplicationEntity;
import jobApplicationApp.entity.ApplicationStatusEntity;
import jobApplicationApp.entity.CompetenceEntity;
import jobApplicationApp.exception.NoMatchException;
import jobApplicationApp.exception.NotValidArgumentException;

import java.util.Collection;

public interface ApplicationDao {
    ApplicationEntity getApplicationById(int id, String language) throws NoMatchException;
    void changeApplicationStatus(int applicationId, ApplicationStatusForm status, String language) throws NotValidArgumentException;
    void insertApplication(ApplicationForm application, String language) throws NotValidArgumentException;
    Collection<ApplicationEntity> getXApplicationsFrom(int startId, int numberOfApplication, String language);
    Collection<ApplicationEntity> getApplicationByParam(ApplicationParamForm param, String language);
    Collection<CompetenceEntity> getAllValidCompetences(String language) throws NotValidArgumentException;
    Collection <ApplicationStatusEntity> getAllValidStatus(String language) throws NotValidArgumentException;
}
