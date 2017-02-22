package service;

import jobApplicationApp.JobApplicationLauncher;
import jobApplicationApp.dao.MysqlApplicationDao;
import jobApplicationApp.dto.form.ApplicationParamForm;
import jobApplicationApp.dto.form.ApplicationStatusForm;
import jobApplicationApp.dto.form.PersonForm;
import jobApplicationApp.dto.response.ApplicationResponse;
import jobApplicationApp.entity.ApplicationEntity;
import jobApplicationApp.entity.ApplicationStatusEntity;
import jobApplicationApp.entity.CompetenceEntity;
import jobApplicationApp.exception.NotValidArgumentException;
import jobApplicationApp.service.JobApplicationService;
import jobApplicationApp.service.UserApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import utils.JobApplicationEntityGenerator;
import utils.JobApplicationFormGenerater;

import java.util.ArrayList;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobApplicationLauncher.class)
@ActiveProfiles("test")
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
