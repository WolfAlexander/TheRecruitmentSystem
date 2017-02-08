package jobApplicationApp.dao;

import jobApplicationApp.dao.repository.*;
import jobApplicationApp.dto.ApplicationForm;
import jobApplicationApp.dto.CompetenceForm;
import jobApplicationApp.entity.*;
import jobApplicationApp.exception.NotValidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import java.util.*;

@Repository
@Qualifier("mysql")
public class MysqlApplicationDao implements ApplicationDao{

    @Autowired
    private EntityManager entityManager;


    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired private ApplicationRepository applicationRepository;
    @Autowired private StatusRepository statusRepository;
    @Autowired private PersonRepository personRepository;
    @Autowired private CompetenceProfileRepository competenceProfileRepository;
    @Autowired private AvailableRepository availableRepository;
    @Autowired private CompetenceRepository competenceRepository;

    @Override
    public ApplicationEntity getApplicationById(int id) {
        return applicationRepository.findOne(id);
    }

    @Override
    public void changeApplicationStatus(int applicationId, String status) {
        ApplicationEntity a = getApplicationById(applicationId);
        ApplicationStatusEntity newStatus = statusRepository.findByName(status);
        a.changeStatus(newStatus);
        applicationRepository.save(a);
    }

    @Override
    public boolean applicationExists(int id) {
        return applicationRepository.exists(id);
    }

    @Override
    public void insertApplication(ApplicationForm application) throws NotValidArgumentException {
        ApplicationStatusEntity status = statusRepository.findByName("PENDING");
        Date registrationDate = new Date();
        PersonEntity person = personRepository.findOne(application.getPersonId());

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
        newApplication = applicationRepository.save(newApplication);
        ArrayList<CompetenceProfileEntity> competenceProfileEntities = new ArrayList<>();
            for (CompetenceForm competenceProfileEntity : application.getCompetenceProfile()) {
                CompetenceEntity competence = competenceRepository.findByName(competenceProfileEntity.getName());
                if(competence == null){
                    throw new NotValidArgumentException("Not valid name on competence");
                }
                CompetenceProfileEntity c = new CompetenceProfileEntity(newApplication,competence, competenceProfileEntity.getYearsOfExperience());
                competenceProfileEntities.add(c);
        }
        competenceProfileEntities.forEach((c)->competenceProfileRepository.save(c));
    }

    @Override
    public Collection<ApplicationEntity> getXApplicationsFrom(int startId, int numberOfApplication) {
        return applicationRepository.getXApplicationsFrom(startId,numberOfApplication);
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


