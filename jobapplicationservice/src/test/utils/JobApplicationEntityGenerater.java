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

        return  new ApplicationEntity(generatePersonEntity(),
                        registrationDate,
                        generateApplicationStatusEntity(),
                        generateAvailabilityEntity());
    }

    public ApplicationStatusEntity generateApplicationStatusEntity(){
        return new ApplicationStatusEntity("PENDING");
    }

    public PersonEntity generatePersonEntity(){
        Date dateOfBirth;
        try {
            dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse("1995-02-14");
        } catch (ParseException e) {
            throw new RuntimeException("can't create person entity");
        }
        return new PersonEntity("Henrik","Gustavsson",dateOfBirth,"henrik.gustavsson@hotmail.com",generateRoleEntity());
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

    public  RoleEntity generateRoleEntity(){
        //return new LocalizedRole(new RoleEntity("Recruiter"),new LanguageEntity("sv"),"Recruiter");
        return new RoleEntity("Recruiter");
    }
}
