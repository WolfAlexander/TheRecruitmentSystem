package jobApplicationApp.dao;

import jobApplicationApp.dao.repository.*;
import jobApplicationApp.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


import java.util.*;

@Repository
@Qualifier("mysql")
public class MysqlApplicationDao implements ApplicationDao{

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired private ApplicationRepository applicationRepository;
    @Autowired private StatusRepository statusRepository;
    @Autowired private PersonRepository personRepository;
    @Autowired private CompetenceProfileRepository competenceProfileRepository;
    @Autowired private AvailableRepository availableRepository;

    @Override
    public ApplicationEntity getApplicationById(int id) {
        return applicationRepository.findOne(id);
    }

    @Override
    public void changeApplicationStatus(int applicationId, ApplicationStatusEntity status) {
        ApplicationEntity a = getApplicationById(applicationId);
        ApplicationStatusEntity newStatus = statusRepository.findByName(status.getName());
        a.changeStatus(newStatus);
        applicationRepository.save(a);
    }

    @Override
    public boolean applicationExists(int id) {
        return applicationRepository.exists(id);
    }

    @Override
    public void insertApplication(ApplicationEntity application) {
        ApplicationStatusEntity status = statusRepository.findByName("PENDING");
        Date registrationDate = new Date();
        PersonEntity person = personRepository.findOne(application.getPerson().getId());

        AvailabilityEntity availability;
        Date from = application.getAvailableForWork().getFromDate();
        Date to = application.getAvailableForWork().getToDate();
        List<AvailabilityEntity> a = availableRepository.findByFromDateAndToDate(from,to);

        if(a.size() == 0){
            availability = new AvailabilityEntity(from,to);
            availableRepository.save(availability);
        }else {
            availability = a.get(0);
        }

        ApplicationEntity newApplication = new ApplicationEntity(person,registrationDate,status,availability);
        applicationRepository.save(newApplication);
        //todo test
        for(CompetenceProfileEntity competenceProfileEntity:application.getCompetenceProfile()){
           CompetenceProfileEntity c = new CompetenceProfileEntity(newApplication, competenceProfileEntity.getCompetence(), competenceProfileEntity.getYearsOfExperience());
           competenceProfileRepository.save(c);
        }

    }

    @Override
    public Collection<ApplicationEntity> getAHundredApplicationsFrom(int index) {
        return applicationRepository.getAHundredApplicationsFrom(index);
    }

    @Override
    public Map<Integer, ApplicationEntity> getAllApplication() {
        Map<Integer,ApplicationEntity> list = new HashMap<>();

        Iterable<ApplicationEntity> applications = applicationRepository.findAll();
        for(ApplicationEntity application : applications){
            list.put(application.getId(),application);
        }

        return list;
    }

}


