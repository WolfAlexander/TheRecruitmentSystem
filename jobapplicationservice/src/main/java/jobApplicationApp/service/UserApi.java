package jobApplicationApp.service;

import jobApplicationApp.dto.AuthRequest;
import jobApplicationApp.dto.AuthTokeResponse;
import jobApplicationApp.dto.form.PersonForm;
import jobApplicationApp.dto.form.RoleForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sun.swing.BakedArrayList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Api to retrieve user information
 *
 * @author Adrian Gortzak gortzak@kth.se
 */
@Service
public class UserApi {

    private String serviceToken;
    private final AuthRequest authRequest = new AuthRequest("jobapplicationservice","password");

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Get user by id
     * @param id of user
     * @return user information
     */
    public PersonForm getUserById(int id){
        createToken();

       // return new PersonForm("Adrian","Gortzak",new Date(2016,02,17),"addegor@hotmail.com",new RoleForm("test"));
        return restTemplate.exchange("http://REGISTRATION-SERVICE/en/persons/"+id, HttpMethod.GET, createRequestEntity(), PersonForm.class).getBody();
    }

    private void createToken() {
        if(serviceToken == null) {
            String newToken = restTemplate.postForObject("http://AUTH-SERVICE/auth/login", authRequest, AuthTokeResponse.class).getToken();
            if (newToken == null) {
                throw new InternalAuthenticationServiceException("token have not been created");
            } else {

                serviceToken = newToken;
            }
        }
    }

    /**
     * Get list of user id's by person name
     * @param name of person
     * @return list of user id's
     */
    public Collection<Integer> getIdOfUsersWithName(String name){
        ArrayList<Integer> test = new ArrayList<>();
        test.add(3);
        test.add(5);
        return test;
    }

    /**
     * Check if user's id is valid
     * @param id to check
     * @return true if it exists
     */
    public boolean validUserId(int id){
        return true;
    }


    private HttpEntity createRequestEntity(){
        return new HttpEntity<>(createRequestHeaders());
    }

    private HttpHeaders createRequestHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", serviceToken);

        return headers;
    }
}
