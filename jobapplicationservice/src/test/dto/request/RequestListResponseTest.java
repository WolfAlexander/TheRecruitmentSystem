package dto.request;

import jobApplicationApp.JobApplicationLauncher;

import jobApplicationApp.dto.response.RequestListResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobApplicationLauncher.class)
public class RequestListResponseTest {
    @Test
    public void getRequstListMessages(){
        Collection<String> errorMessages = new ArrayList<>();
        errorMessages.add("not running");
        errorMessages.add("not running2");
        RequestListResponse requestListResponse = new RequestListResponse(errorMessages);

        Collection<String> expectedErrorMessages = new ArrayList<>();
        expectedErrorMessages.add("not running");
        expectedErrorMessages.add("not running2");

        requestListResponse.getMessages().forEach((k)->{
            if(expectedErrorMessages.contains(k)){
                expectedErrorMessages.remove(k);
            }
        });
        assertThat(expectedErrorMessages.size()).isEqualTo(0);

    }
}
