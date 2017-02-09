package dto.form;

import jobApplicationApp.JobApplicationLauncher;
import jobApplicationApp.dto.form.ApplicationForm;
import jobApplicationApp.dto.form.AvailabilityForm;
import jobApplicationApp.dto.form.CompetenceForm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobApplicationLauncher.class)
public class ApplicationFormTest {

    private ApplicationForm applicationForm;


    @Before
    public void setup(){
        Date toDate = null;
        Date fromDate = null;
        try {
            fromDate = new SimpleDateFormat("yyyy-MM-dd").parse("1995-02-14");
            toDate = new SimpleDateFormat("yyyy-MM-dd").parse("2016-02-17");
        } catch (ParseException e) {
            fail("Could not create dateOfBirth from string");
        }
        AvailabilityForm availabilityForm = new AvailabilityForm(fromDate,toDate);
        Collection<CompetenceForm> competenceForms = new ArrayList<>();
        competenceForms.add(new CompetenceForm("running",3));
        competenceForms.add(new CompetenceForm("test",3));

        applicationForm = new ApplicationForm(1,availabilityForm,competenceForms);
    }


    @Test
    public void applicationFormTestGetPersonId(){
        assertEquals(applicationForm.getPersonId(),1);
    }

    @Test
    public void applicationFormTestGetCompetences(){
        Collection<String> expectedCompetences = new ArrayList<>();
        expectedCompetences.add("running");
        expectedCompetences.add("test");

        applicationForm.getCompetenceProfile().forEach((k)->{
            if(expectedCompetences.contains(k.getName())){
                expectedCompetences.remove(k.getName());
            }
        });
        assertEquals(expectedCompetences.size(),0);
    }

}
