package utils;

import jobApplicationApp.dto.form.ApplicationForm;
import jobApplicationApp.dto.form.ApplicationStatusForm;
import jobApplicationApp.dto.form.AvailabilityForm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JobApplicationFormGenerater {

    public ApplicationForm generateApplicationForm(){


        return new ApplicationForm(5,this.getAvailabilityForm(),null);

    }

    public ApplicationStatusForm getApplicationStatusForm(){

        return new ApplicationStatusForm("PENDING");
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
