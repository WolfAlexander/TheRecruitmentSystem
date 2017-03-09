package jobApplicationApp.dao;

import jobApplicationApp.dao.repository.*;
import jobApplicationApp.dao.repository.localized.LanguageRepository;
import jobApplicationApp.dao.repository.localized.LocalizedCompetenceRepository;
import jobApplicationApp.dao.repository.localized.LocalizedStatusRepository;
import jobApplicationApp.dto.form.*;
import jobApplicationApp.entity.*;
import jobApplicationApp.entity.localized.LanguageEntity;
import jobApplicationApp.entity.localized.LocalizedCompetence;
import jobApplicationApp.entity.localized.LocalizedStatus;
import jobApplicationApp.exception.NoMatchException;
import jobApplicationApp.exception.NotValidArgumentException;
import jobApplicationApp.service.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

/**
 * DAO for mysql implementation
 */
@Repository
@Qualifier("mysql")
public class MysqlApplicationDao implements ApplicationDao{

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired private ApplicationRepository applicationRepository;
    @Autowired private ApplicationStatusRepository statusRepository;
    @Autowired private CompetenceProfileRepository competenceProfileRepository;
    @Autowired private AvailableRepository availableRepository;
    @Autowired private CompetenceRepository competenceRepository;
    @Autowired private LanguageRepository languageRepository;
    @Autowired private LocalizedStatusRepository localizedStatusRepository;
    @Autowired private LocalizedCompetenceRepository localizedCompetenceRepository;
    @Autowired private UserApi userApi;
    @PersistenceContext private EntityManager em;

    /**
     * Get an application specified by id, in the wanted language
     * @param id of application
     * @param language of application
     * @return an application with the id, translated to the language
     */
    public ApplicationEntity getApplicationById(int id,String language) throws NoMatchException {

        ApplicationEntity application = applicationRepository.findOne(id);
        if(application == null){
            log.info("asked for application with id "+ id +" id didn't exist");
            throw new NoMatchException("no application with that id");
        }
        return translateApplication(application, language);
    }



    /**
     * Changes the application status in the database by id
     * @param applicationId of application to change status on
     * @param status to change application to
     * @param language of status
     * @throws NotValidArgumentException if translation of status doesn't exist
     */
    public void changeApplicationStatus(int applicationId, ApplicationStatusForm status, String language) throws NotValidArgumentException {
        ApplicationEntity application = getApplicationById(applicationId,language);
        LocalizedStatus localizedStatus = localizedStatusRepository.getByLanguageIdAndTranslation(getLanguage(language).getId(),status.getName());
        if(localizedStatus == null){
            log.error("invalid status type [" + status.getName() + "]");
            throw new NotValidArgumentException("Non existing status type");
        }
        ApplicationStatusEntity newStatus = statusRepository.findOne(localizedStatus.getStatusId());
        application.changeStatus(newStatus);
        applicationRepository.save(application);
    }

    /**
     * Insert new application to database
     * @param application to be inserted into database
     * @param language of application parameters
     * @throws NotValidArgumentException if competence doesn't exist
     */
    public void insertApplication(ApplicationForm application, String language) throws NotValidArgumentException {
        ApplicationStatusEntity status = statusRepository.findByName("PENDING");
        Date registrationDate = new Date();
        AvailabilityEntity availability = getAvailability(application.getAvailableForWork());

        ApplicationEntity newApplication = new ApplicationEntity(application.getPersonId(),registrationDate,status,availability);
        newApplication = applicationRepository.save(newApplication);
        saveCompetenceProfilesToApplication(application.getCompetenceProfile(), newApplication, language);
    }

    /**
     * Get availability entity for application
     * @param availabilityForm for start date and end date
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
     * @param newApplication to attach the competences to
     * @param language of competences
     * @throws NotValidArgumentException if competence can't be found
     */
    private void saveCompetenceProfilesToApplication(Collection<CompetenceForm> competenceLists, ApplicationEntity newApplication, String language) throws NotValidArgumentException{
        ArrayList<CompetenceProfileEntity> competenceProfileEntities = new ArrayList<>();
        for (CompetenceForm competenceProfileEntity : competenceLists) {

            LocalizedCompetence lc = localizedCompetenceRepository.getByLanguageIdAndTranslation(getLanguage(language).getId(),competenceProfileEntity.getName());
            if(lc == null){
                throw new NotValidArgumentException("Not valid name on competence");
            }
            CompetenceEntity competence = competenceRepository.findOne(lc.getCompetenceId());
            CompetenceProfileEntity c = new CompetenceProfileEntity(newApplication,competence, competenceProfileEntity.getYearsOfExperience());
            competenceProfileEntities.add(c);
        }
        competenceProfileEntities.forEach((c)->competenceProfileRepository.save(c));
    }

    /**
     * Get a specified number of applications from a specified application defined by id from database
     * @param startId is the start point of the list of applications
     * @param numberOfApplication to retrieve
     * @param language of applications
     * @return collection of applications
     */
    public Collection<ApplicationEntity> getXApplicationsFrom(int startId, int numberOfApplication, String language) {
        Collection<ApplicationEntity> listOfApplications = applicationRepository.getXApplicationsFrom(startId);
        listOfApplications.forEach((application)->{
            application = translateApplication(application,language);
        });
        return listOfApplications;
    }

    /**
     * Get applications by parameters
     * @param param to filter with
     * @param language of application's parameters
     * @return a collection of applications
     */
    public Collection<ApplicationEntity> getApplicationByParam(ApplicationParamForm param, String language) {
        Collection<ApplicationEntity> resultListOfApplication= new ArrayList<>();
        Collection<ApplicationEntity> newResultOfApplicationFilter = new ArrayList<>();
        try {
            if (param.getAvailableForWork() != null) {
                resultListOfApplication = filterReturnHandler(filterApplicationsByAvailability(param.getAvailableForWork()),resultListOfApplication);
            }
            if (param.getCompetenceProfile() != null) {
                resultListOfApplication = filterReturnHandler(filterApplicationsByCompetence(param.getCompetenceProfile(),language),resultListOfApplication);
            }
            if (param.getName() != null) {
                resultListOfApplication = filterReturnHandler(filterApplicationsByName(param.getName()),resultListOfApplication);
            }
        }catch (NotValidArgumentException e){
            log.info(e.toString());
            throw e;
        }catch (NoMatchException e){
            log.info(e.toString());
            return new ArrayList<>();
        }
        resultListOfApplication = translateListOfApplications(resultListOfApplication,language);
        return resultListOfApplication;
    }


    /**
     * Handler for each part of search, deciding if there will be no result, if it's the first search or should do an intersection between all results
     * @param newResultOfApplicationFilter is the return from the previous search
     * @param resultListOfApplication is the resulting list of previous searches
     * @return resulting list of application after one part os filter
     */
    private Collection<ApplicationEntity> filterReturnHandler(Collection<ApplicationEntity> newResultOfApplicationFilter, Collection<ApplicationEntity> resultListOfApplication){
        if (newResultOfApplicationFilter.size() == 0) {
            throw new NoMatchException("No matches will be found");
        }
        if (resultListOfApplication.size() == 0) {
            resultListOfApplication = newResultOfApplicationFilter;
        } else {
            resultListOfApplication = getListOfIntersectionBetweenApplicationList(newResultOfApplicationFilter, resultListOfApplication);
        }
        return resultListOfApplication;
    }


    /**
     * Filter application by first name
     * @param name of user
     * @return list of application with the required name
     */
    private Collection<ApplicationEntity> filterApplicationsByName(String name){
        ArrayList<ApplicationEntity> newResultOfApplicationFilter = new ArrayList();
        Collection<Integer> userIds = userApi.getIdOfUsersWithName(name);
        for(ApplicationEntity application : applicationRepository.findAll()){
            if(userIds.contains(Integer.valueOf(application.getPersonId()))){
                newResultOfApplicationFilter.add(application);
            }
        }
        return newResultOfApplicationFilter;
    }

    /**
     * Filter applications by availability
     * @param availabilityForm that have from and to date of availability from user
     * @return collection of applications that match requirements.
     * @throws NoMatchException if no application could be found on a search by parameters
     */
    private Collection<ApplicationEntity> filterApplicationsByAvailability(AvailabilityForm availabilityForm) throws NoMatchException {
        Collection<ApplicationEntity> resultListOfApplication= new ArrayList<>();
        Collection<ApplicationEntity> sqlResultOfApplication;
        if (availabilityForm != null) {
            resultListOfApplication = applicationRepository.getApplicationsThatCanWorkFrom(availabilityForm.getFromDate());
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
     * Filter applications by competences
     * @param competenceFormList list of competences that is required of the application
     * @return collection of applications that match requirements.
     * @throws RuntimeException if no application could be found on a search by parameters
     */
    private Collection<ApplicationEntity> filterApplicationsByCompetence(Collection<CompetenceForm> competenceFormList, String language) throws RuntimeException {
        Collection<ApplicationEntity> resultListOfApplication= new ArrayList<>();
        Collection<ApplicationEntity> sqlResultOfApplication;

        StringBuilder sqlCondition = new StringBuilder("SELECT a FROM ApplicationEntity a WHERE 1=1 ");
        for(CompetenceForm cf : competenceFormList){
            LocalizedCompetence localizedCompetence = localizedCompetenceRepository.getByLanguageIdAndTranslation(getLanguage(language).getId(),cf.getName());
            if(localizedCompetence== null){
                throw new NotValidArgumentException("Not existing competence");
            }else {
                sqlCondition.append("AND " +localizedCompetence.getCompetenceId()+ " IN (SELECT co.competence.id FROM CompetenceProfileEntity co WHERE co.application.id=a.id)");
            }

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
     * @param language of competences
     * @return collection of competences
     */
    public Collection<CompetenceEntity> getAllValidCompetences(String language) {
        Collection<CompetenceEntity> ce = new ArrayList<>();
        competenceRepository.findAll().forEach((c)-> {
            c.setName(translateCompetence(c, getLanguage(language)));
            ce.add(c);
        });
        return ce;
    }

    /**
     * Get allowed application statuses on applications from database
     * @return collection of application status
     * @param language of statuses
     */
    public Collection<ApplicationStatusEntity> getAllValidStatus(String language) {
        Collection<ApplicationStatusEntity> ase = new ArrayList<>();
        statusRepository.findAll().forEach((c)->{
            c.setName(translateStatus(c, getLanguage(language)));
            ase.add(c);
        });
        return ase;
    }

    /**
     * Makes the intersection of two lists of applications
     * @param map1 first list of applications
     * @param map2 second list of applications
     * @return collection of applications that intersect with each other.
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

    /**
     * Translate an application to another language
     * @param application to translate
     * @param language to translate to
     * @return translated application
     */
    private ApplicationEntity translateApplication(ApplicationEntity application, String language){
        ApplicationEntity applicationEntity = application;

        LanguageEntity lang = getLanguage(language);
        application.getStatus().setName(translateStatus(applicationEntity.getStatus(), lang));

        for(CompetenceProfileEntity c : application.getCompetenceProfile()){
            c.getCompetence().setName(translateCompetence(c.getCompetence(),lang));
        }

        return applicationEntity;
    }

    /**
     * Translate a list of application
     * @param listOfApplication to translate
     * @param language of translation
     * @return a list of translated application
     */
    private Collection<ApplicationEntity> translateListOfApplications(Collection<ApplicationEntity> listOfApplication, String language){
        listOfApplication.forEach((application)->{
            application = translateApplication(application,language);
        });
        return listOfApplication;
    }

    /**
     * Translate application status
     * @param StatusEntity to translate
     * @param language of translation
     * @return translated application status
     * @throws NotValidArgumentException if the status can't be translated
     */
    private String translateStatus(ApplicationStatusEntity StatusEntity, LanguageEntity language) throws NotValidArgumentException {
        LocalizedStatus localizedStatus = localizedStatusRepository.getByLanguageIdAndStatusId(language.getId(),StatusEntity.getId());
        return localizedStatus.getTranslation();
    }

    /**
     * Translate application competence
     * @param competenceProfile to translate
     * @param language of translation
     * @return translated competence
     * @throws NotValidArgumentException if the competence can't be translated
     */
    private String translateCompetence(CompetenceEntity competenceProfile, LanguageEntity language) throws NotValidArgumentException{
        LocalizedCompetence localizedCompetence = localizedCompetenceRepository.getByLanguageIdAndCompetenceId(language.getId(),competenceProfile.getId());
        return localizedCompetence.getTranslation();
    }

    /**
     * Get language entity by its name
     * @param language name of language
     * @return language entity
     * @throws NotValidArgumentException if the language can't be found
     */
    private LanguageEntity getLanguage(String language) throws NotValidArgumentException{
        LanguageEntity l = languageRepository.findByName(language);
        if(l == null){
            throw new NotValidArgumentException("Not a supported language");
        }
        return l;
    }
}


