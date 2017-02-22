package utils;

import jobApplicationApp.dto.form.*;
import jobApplicationApp.dto.response.ApplicationResponse;
import jobApplicationApp.entity.ApplicationEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class JobApplicationFormGenerater {

    private JobApplicationEntityGenerator jobApplicationEntityGenerator = new JobApplicationEntityGenerator();

    public ApplicationResponse generateApplicationResponse(){
        return new ApplicationResponse(jobApplicationEntityGenerator.generateApplicationEntity(),generatePersonForm());
    }

    private PersonForm generatePersonForm(){
        return new PersonForm("Lars","Henriksson",new Date(1993,12,32),"test@henriksson.con", new RoleForm("test"));
    }

    public ApplicationForm generateApplicationForm(){
        return new ApplicationForm(5,this.getAvailabilityForm(),generateListCompetenceFormWithSize(10));
    }

    public ApplicationStatusForm getApplicationStatusForm(){
        return new ApplicationStatusForm("PENDING");
    }

    public CompetenceForm generateCompetenceForm(){
        return new CompetenceForm("running",1);
    }


    public Collection<CompetenceForm> generateListCompetenceFormWithSize(int size){
        ArrayList<CompetenceForm> a = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            a.add(generateCompetenceForm());
        }
        return a;
    }


    public AvailabilityForm getAvailabilityForm(){
        Date fromDate;
        Date toDate;
        try {
            fromDate = new SimpleDateFormat("yyyy-MM-dd").parse("1995-02-14");
            toDate = new SimpleDateFormat("yyyy-MM-dd").parse("2016-02-17");
        } catch (ParseException e) {
            throw new RuntimeException("can't create date");
        }

       return new AvailabilityForm(fromDate,toDate);
    }

}
