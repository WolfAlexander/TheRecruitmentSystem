package dto.form;

import jobApplicationApp.JobApplicationLauncher;
import jobApplicationApp.dto.form.ApplicationStatusForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = JobApplicationLauncher.class)
public class ApplicationStatusFormTest {

    @Test
    public void getAplicationStatusFormName(){
        ApplicationStatusForm applicationStatusFormTest = new ApplicationStatusForm("test");
        assertEquals(applicationStatusFormTest.getName(),"test");

    }


}
