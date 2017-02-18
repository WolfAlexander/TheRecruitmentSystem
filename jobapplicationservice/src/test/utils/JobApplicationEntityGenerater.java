package utils;

import jobApplicationApp.entity.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class JobApplicationEntityGenerater {

    public ApplicationEntity generateApplicationEntity(){
        Date registrationDate;
        try {
            registrationDate = new SimpleDateFormat("yyyy-MM-dd").parse("2016-02-17");
        } catch (ParseException e) {
            throw new RuntimeException("can't create date");
        }

        return  new ApplicationEntity(4,
                        registrationDate,
                        generateApplicationStatusEntity(),
                        generateAvailabilityEntity());
    }

    public ApplicationStatusEntity generateApplicationStatusEntity(){
        return new ApplicationStatusEntity("PENDING");
    }

    public AvailabilityEntity generateAvailabilityEntity(){
        Date fromDate;
        Date toDate;
        try {
            fromDate = new SimpleDateFormat("yyyy-MM-dd").parse("2016-02-17");
            toDate =  new SimpleDateFormat("yyyy-MM-dd").parse("2017-02-17");
        } catch (ParseException e) {
            throw new RuntimeException("can't create date");
        }
        return  new AvailabilityEntity(fromDate,toDate);
    }

}
