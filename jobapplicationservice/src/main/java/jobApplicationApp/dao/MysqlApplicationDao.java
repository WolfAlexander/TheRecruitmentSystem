package jobApplicationApp.dao;

import jobApplicationApp.dao.repository.*;
import jobApplicationApp.dao.repository.localized.LanguageRepository;
import jobApplicationApp.dao.repository.localized.LocalizedCompetenceRepository;
import jobApplicationApp.dao.repository.localized.LocalizedRoleRepository;
import jobApplicationApp.dao.repository.localized.LocalizedStatusRepository;
import jobApplicationApp.dto.form.*;
import jobApplicationApp.entity.*;
import jobApplicationApp.entity.localized.LanguageEntity;
import jobApplicationApp.entity.localized.LocalizedCompetence;
import jobApplicationApp.entity.localized.LocalizedRole;
import jobApplicationApp.entity.localized.LocalizedStatus;
import jobApplicationApp.exception.NoMatchException;
import jobApplicationApp.exception.NotValidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Repository
@Qualifier("mysql")
public class MysqlApplicationDao implements ApplicationDao{

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired private ApplicationRepository applicationRepository;
    @Autowired private ApplicationStatusRepository statusRepository;
    @Autowired private PersonRepository personRepository;
    @Autowired private CompetenceProfileRepository competenceProfileRepository;
    @Autowired private AvailableRepository availableRepository;
    @Autowired private CompetenceRepository competenceRepository;
    @Autowired private LanguageRepository languageRepository;
    @Autowired private LocalizedRoleRepository localizedRoleRepository;
    @Autowired private LocalizedStatusRepository localizedStatusRepository;
    @Autowired private LocalizedCompetenceRepository localizedCompetenceRepository;

    @PersistenceContext
    private EntityManager em;

    /**
     * Get a application specified by id, in the wished language
     * @param id of application
     * @param language of application
     * @return an application with the id and translated to the language
     */
    @Override
    public ApplicationEntity getApplicationById(int id,String language) {
        ApplicationEntity application = applicationRepository.findOne(id);
        return translateApplication(application, language);
    }

    private ApplicationEntity translateApplication(ApplicationEntity application, String language){
        ApplicationEntity applicationEntity = application;
        try {
            LanguageEntity lang = getLanguage(language);
            applicationEntity = translateRole(applicationEntity, lang);
            applicationEntity = translateStatus(applicationEntity, lang);
            applicationEntity = translateCompetence(applicationEntity, lang);
        }catch (Exception e){
            e.printStackTrace();
        }
        return applicationEntity;
    }

    private ApplicationEntity translateRole(ApplicationEntity application, LanguageEntity lang){
        ApplicationEntity applicationEntity = application;
        LocalizedRole localizedRole = localizedRoleRepository.getByLanguageIdAndRoleId(lang.getId(),application.getPerson().getRole().getId());
        applicationEntity.getPerson().getRole().setName(localizedRole.getTranslation());
        return applicationEntity;
    }

    private ApplicationEntity translateStatus(ApplicationEntity application, LanguageEntity lang) {
        ApplicationEntity applicationEntity = application;
        LocalizedStatus localizedStatus = localizedStatusRepository.getByLanguageIdAndStatusId(lang.getId(),application.getStatus().getId());
        applicationEntity.getStatus().setName(localizedStatus.getTranslation());
        return applicationEntity;
    }

    private ApplicationEntity translateCompetence(ApplicationEntity application, LanguageEntity lang){
        ApplicationEntity applicationEntity = application;
        for(CompetenceProfileEntity c : application.getCompetenceProfile()){
            LocalizedCompetence localizedCompetence = localizedCompetenceRepository.getByLanguageIdAndCompetenceId(lang.getId(),c.getCompetence().getId());
            c.getCompetence().setName(localizedCompetence.getTranslation());
        }
        //LocalizedCompetence localizedCompetence = localizedCompetenceRepository.getByLanguageIdAndCompetenceId(lang.getId(),)
        return applicationEntity;
    }

    private LanguageEntity getLanguage(String lang){
        return languageRepository.findByName(lang);
    }

    /**
     * Changes the application status in database by od
     * @param applicationId of application to change status on
     * @param status to change application to
     * @throws NotValidArgumentException
     */
    @Override
    public void changeApplicationStatus(int applicationId, ApplicationStatusForm status) throws NotValidArgumentException {
      /*  ApplicationEntity a = getApplicationById(applicationId);
        ApplicationStatusEntity newStatus = statusRepository.findByName(status.getName());
        if(newStatus == null) throw new NotValidArgumentException("Non existing status type");
        a.changeStatus(newStatus);
        applicationRepository.save(a); */
    }

    /**
     * Insert new application to database
     * @param application to be inserted into database
     * @throws NotValidArgumentException
     */
    @Override
    public void insertApplication(ApplicationForm application) throws NotValidArgumentException {
        ApplicationStatusEntity status = statusRepository.findByName("PENDING");
        Date registrationDate = new Date();
        PersonEntity person = personRepository.findOne(application.getPersonId());
        AvailabilityEntity availability = getAvailability(application.getAvailableForWork());

        ApplicationEntity newApplication = new ApplicationEntity(person,registrationDate,status,availability);
        newApplication = applicationRepository.save(newApplication);
        saveCompetenceProfilesToApplication(application.getCompetenceProfile(), newApplication);
    }

    /**
     * Get availability entity for application
     * @param availabilityForm for from and to date
     * @return a valid availability entity
     */
    private AvailabilityEntity getAvailability(AvailabilityForm availabilityForm){
        AvailabilityEntity availability;
        AvailabilityEntity a = availableRepository.findByFromDateAndToDate(availabilityForm.getFromDate(),availabilityForm.getToDate());

        if(a == null){
            availability = new AvailabilityEntity(availabilityForm.getFromDate(),availabilityForm.getToDate());
            return availableRepository.save(availability);
        }else {
            return a;
        }
    }

    /**
     * Save competence profiles to application
     * @param competenceLists of competences to be added to the application
     * @param newApplication to attached the competences to
     */
    private void saveCompetenceProfilesToApplication(Collection<CompetenceForm> competenceLists, ApplicationEntity newApplication){
        ArrayList<CompetenceProfileEntity> competenceProfileEntities = new ArrayList<>();
        for (CompetenceForm competenceProfileEntity : competenceLists) {
            CompetenceEntity competence = competenceRepository.findByName(competenceProfileEntity.getName());
            if(competence == null){
                throw new NotValidArgumentException("Not valid name on competence");
            }
            log.info("years of experience" +  competenceProfileEntity.getYearsOfExperience());
            CompetenceProfileEntity c = new CompetenceProfileEntity(newApplication,competence, competenceProfileEntity.getYearsOfExperience());
            competenceProfileEntities.add(c);
        }
        competenceProfileEntities.forEach((c)->competenceProfileRepository.save(c));
    }

    /**
     * Get a specified number of application from a specified application defined by id from database
     * @param startId is the start point of the list of application
     * @param numberOfApplication to retrieve
     * @return collection of applications
     */
    @Override
    public Collection<ApplicationEntity> getXApplicationsFrom(int startId, int numberOfApplication) {
        return applicationRepository.getXApplicationsFrom(startId,numberOfApplication);
    }

    /**
     * Get applications by parameters
     * @param param to filter with
     * @return a collection of applications
     */
    @Override
    public Collection<ApplicationEntity> getApplicationByParam(ApplicationParamForm param) {
        Collection<ApplicationEntity> resultListOfApplication= new ArrayList<>();
        Collection<ApplicationEntity> newResultOfApplicationFilter;
        try {
            if (param.getAvailableForWork() != null) {
                resultListOfApplication = filterApplicationsByAvailability(param.getAvailableForWork());
            }
            if (param.getCompetenceProfile() != null) {
                newResultOfApplicationFilter = filterApplicationsByCompetence(param.getCompetenceProfile());
                if (resultListOfApplication.size() == 0) {
                    resultListOfApplication = newResultOfApplicationFilter;
                } else {
                    getListOfIntersectionBetweenApplicationList(resultListOfApplication, newResultOfApplicationFilter);
                }
            }
            if (param.getName() != null) {
                newResultOfApplicationFilter = applicationRepository.getApplicationsByPersonName(param.getName());
                if (newResultOfApplicationFilter.size() == 0) {
                    throw new NoMatchException("No matches will be found");
                }
                if (resultListOfApplication.size() == 0) {
                    resultListOfApplication = newResultOfApplicationFilter;
                } else {
                    resultListOfApplication = getListOfIntersectionBetweenApplicationList(newResultOfApplicationFilter, resultListOfApplication);
                }
            }
        }catch (NotValidArgumentException e){
            throw e;
        }catch (NoMatchException e){
            return new ArrayList<>();
        }
        return resultListOfApplication;
    }

    /**
     * Filter applications by availability
     * @param availabilityForm that have from and to date of availability from user
     * @return collection of applications that match requirements.
     * @throws RuntimeException if no application could be found on a search by parameters
     */
    private Collection<ApplicationEntity> filterApplicationsByAvailability(AvailabilityForm availabilityForm) throws RuntimeException {
        Collection<ApplicationEntity> resultListOfApplication= new ArrayList<>();
        Collection<ApplicationEntity> sqlResultOfApplication;
        if (availabilityForm != null) {
            sqlResultOfApplication = applicationRepository.getApplicationsThatCanWorkFrom(availabilityForm.getFromDate());
                resultListOfApplication = sqlResultOfApplication;
        }
        if (availabilityForm.getToDate() != null) {
            sqlResultOfApplication = applicationRepository.getApplicationsThatCanWorkTo(availabilityForm.getToDate());
            if (sqlResultOfApplication.size() == 0) {throw new NoMatchException("No matches will be found");}
            if(resultListOfApplication.size() == 0){
                resultListOfApplication = sqlResultOfApplication;
            }else {
                resultListOfApplication = getListOfIntersectionBetweenApplicationList(sqlResultOfApplication, resultListOfApplication);
            }
        }
        return resultListOfApplication;
    }

    /**
     *
     * @param competenceFormList list of competences that is required of the application
     * @return ollection of applications that match requirements.
     * @throws RuntimeException if no application could be found on a search by parameters
     */
    private Collection<ApplicationEntity> filterApplicationsByCompetence(Collection<CompetenceForm> competenceFormList) throws RuntimeException {
        Collection<ApplicationEntity> resultListOfApplication= new ArrayList<>();
        Collection<ApplicationEntity> sqlResultOfApplication;
        StringBuilder sqlCondition = new StringBuilder("SELECT a FROM ApplicationEntity a WHERE 1=1 ");
        for(CompetenceForm cf : competenceFormList){
            CompetenceEntity competenceEntity = competenceRepository.findByName(cf.getName());
            if(competenceEntity== null){
                throw new NotValidArgumentException("Not existing competence");
            }else {
                sqlCondition.append("AND " +competenceEntity.getId()+ " IN (SELECT co.competence.id FROM CompetenceProfileEntity co WHERE co.application.id=a.id)");
            }
            log.info(sqlCondition.toString());
            Query query = em.createQuery(sqlCondition.toString());
            sqlResultOfApplication =  query.getResultList();
            if (sqlResultOfApplication.size() == 0) {throw new NoMatchException("No matches will be found");};
            if(resultListOfApplication.size() == 0){
                resultListOfApplication = sqlResultOfApplication;
            }else {
                resultListOfApplication = getListOfIntersectionBetweenApplicationList(sqlResultOfApplication, resultListOfApplication);
            }
        }
        return resultListOfApplication;
    }

    /**
     * Get all valid competences allowed on applications form database
     * @return collection of competences
     */
    @Override
    public Collection<CompetenceEntity> getAllValidCompetences() {
        Collection<CompetenceEntity> ce = new ArrayList<>();
        competenceRepository.findAll().forEach((c)->ce.add(c));
        return ce;
    }

    /**
     * Get allowed application statuses on applications from database
     * @return collection of application status
     */
    @Override
    public Collection<ApplicationStatusEntity> getAllValidStatus() {
        Collection<ApplicationStatusEntity> ase = new ArrayList<>();
        statusRepository.findAll().forEach((c)->ase.add(c));
        return ase;
    }

    /**
     * Makes the intersection of two lists of application
     * @param map1 first list of application
     * @param map2 second list of application
     * @return collection of application that intersect with each other.
     */
    private Collection<ApplicationEntity> getListOfIntersectionBetweenApplicationList(Collection<ApplicationEntity> map1, Collection<ApplicationEntity> map2){
        Collection<ApplicationEntity> applicationListResult = new ArrayList<>();
        map1.forEach((k)->{
            if(map2.contains(k)){
                applicationListResult.add(k);
            }
        });
        return applicationListResult;
    }
}


