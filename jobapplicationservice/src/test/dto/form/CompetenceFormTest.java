package dto.form;


import jobApplicationApp.JobApplicationLauncher;
import jobApplicationApp.dto.form.CompetenceForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobApplicationLauncher.class)
public class CompetenceFormTest {

    @Test
    public void getNameOfCompetenceTest(){
        Collection<CompetenceForm> competenceForms = new ArrayList<>();
        competenceForms.add(new CompetenceForm("running",3));
        competenceForms.add(new CompetenceForm("test",3));

        Collection<String> expectedCompetences = new ArrayList<>();
        expectedCompetences.add("running");
        expectedCompetences.add("test");

        competenceForms.forEach((k)->{
            if(expectedCompetences.contains(k.getName())){
                expectedCompetences.remove(k.getName());
            }
        });
    }


}
