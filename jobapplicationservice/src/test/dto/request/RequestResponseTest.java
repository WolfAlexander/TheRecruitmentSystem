package dto.request;

import jobApplicationApp.JobApplicationLauncher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Ignore
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = JobApplicationLauncher.class)
public class RequestResponseTest {
    private String message;
}
