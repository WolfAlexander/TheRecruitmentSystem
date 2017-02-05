package jobApplicationApp.dao;

import jobApplicationApp.entity.ApplicationEntity;
import jobApplicationApp.entity.ApplicationStatusEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class MysqlApplicationDao implements ApplicationDao{
    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public ApplicationEntity getById(int id) {
        return applicationRepository.findOne(id);
    }

    //todo not tested
    @Override
    public void changeStatus(int applicationId, ApplicationStatusEntity status) {
        ApplicationEntity a = getById(applicationId);
        a.setStatus(status);
        applicationRepository.save(a);
    }

    @Override
    public boolean exists(int id) {
        return applicationRepository.exists(id);
    }
    //Todo not tested
    @Override
    public void insert(ApplicationEntity application) {
        applicationRepository.save(application);
    }

    //todo lookup if there is a easy way
    @Override
    public Collection<ApplicationEntity> getAHundredApplicationsFrom(int index) {
        return null;
    }
}
