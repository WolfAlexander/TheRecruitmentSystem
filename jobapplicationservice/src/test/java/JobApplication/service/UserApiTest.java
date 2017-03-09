package JobApplication.service;

import JobApplication.utils.JobApplicationEntityGenerator;
import JobApplication.utils.JobApplicationFormGenerater;
import jobApplicationApp.JobApplicationLauncher;
import jobApplicationApp.dto.form.PersonForm;
import jobApplicationApp.service.UserApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Collection;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobApplicationLauncher.class)
@ActiveProfiles("testing")
public class UserApiTest {

    private JobApplicationFormGenerater jobApplicationFormGenerater = new JobApplicationFormGenerater();
    private JobApplicationEntityGenerator jobApplicationEntityGenerator = new JobApplicationEntityGenerator();
    UserApi userApi = new UserApi();

    @Test
    public void getUserById(){
        assertThat(userApi.getUserById(2)).isInstanceOf(PersonForm.class);
    }

    @Test
    public void getIdsOfUsersWithName(){
        assertThat(userApi.getIdOfUsersWithName("henrik")).isInstanceOf(Collection.class);
    }

    @Test
    public void validUserId(){
        assertThat(userApi.validUserId(1)).isInstanceOf(Boolean.class);
    }
}
