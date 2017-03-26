package JobApplication.utils;

import jobApplicationApp.entity.*;
import jobApplicationApp.entity.localized.LocalizedCompetence;
import jobApplicationApp.entity.localized.LocalizedStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class JobApplicationEntityGenerator {

    public ApplicationEntity generateApplicationEntity() {
        Date registrationDate;
        try {
            registrationDate = new SimpleDateFormat("yyyy-MM-dd").parse("2016-02-17");
        } catch (ParseException e) {
            throw new RuntimeException("can't create date");
        }

        return new ApplicationEntity(1,1,
                registrationDate,
                generateApplicationStatusEntity(),
                generateAvailabilityEntity(),
                listOfCompetenceProfileEntitiesWithSize(3));
    }


    public CompetenceProfileEntity generateCompetenceProfile(){
        return new CompetenceProfileEntity(generateCompetence(),10);
    }

    public Collection<CompetenceProfileEntity> listOfCompetenceProfileEntitiesWithSize(int size){
        ArrayList<CompetenceProfileEntity> competenceEntities = new ArrayList<>();
        for(int i=0; i<size; i++){
            competenceEntities.add(generateCompetenceProfile());
        }
        return competenceEntities;
    }


    public ApplicationStatusEntity generateApplicationStatusEntity() {
        return new ApplicationStatusEntity(1,"PENDING");
    }

    public Collection<ApplicationEntity> generateListOfApplicationWithTheSize(int size){
        ArrayList<ApplicationEntity> applicationEntities = new ArrayList<>();
        for(int i=0; i<size; i++){
            applicationEntities.add(generateApplicationEntity());
        }
        return applicationEntities;
    }

    public AvailabilityEntity generateAvailabilityEntity() {
        Date fromDate;
        Date toDate;
        try {
            fromDate = new SimpleDateFormat("yyyy-MM-dd").parse("2016-02-17");
            toDate = new SimpleDateFormat("yyyy-MM-dd").parse("2017-02-17");
        } catch (ParseException e) {
            throw new RuntimeException("can't create date");
        }
        return new AvailabilityEntity(fromDate, toDate);
    }

    public Collection<ApplicationEntity> generateApplicationEntityList() {
        ArrayList<ApplicationEntity> a = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            a.add(generateApplicationEntity());
        }
        return a;
    }

    public Collection<ApplicationStatusEntity> generateListOfApplicationStatus(){
        ArrayList<ApplicationStatusEntity> a = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            a.add(generateApplicationStatusEntity());
        }
        return a;
    }

    public CompetenceEntity generateCompetence(){
        return new CompetenceEntity(1,"running");
    }

    public Collection<CompetenceEntity> generateListOfCompetences() {
        ArrayList<CompetenceEntity> a = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            a.add(generateCompetence());
        }
        return a;
    }

    public LocalizedStatus generateLocalizedStatus(){
        return new LocalizedStatus(1,1,"PENDING");
    }

    public LocalizedCompetence generateLocalizedCompetence() {
        return new LocalizedCompetence(1,2,"running");
    }
}