package jobApplicationApp.service;

import jobApplicationApp.dto.form.PersonForm;
import jobApplicationApp.dto.form.RoleForm;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sun.swing.BakedArrayList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Api to retrieve user information
 */
@Service
public class UserApi {

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Get user by id
     * @param id of user
     * @return user information
     */
    public PersonForm getUserById(int id){
        return new PersonForm("Adrian","Gortzak",new Date(2016,02,17),"addegor@hotmail.com",new RoleForm("test"));
        //ResponseEntity<String> response = restTemplate.getForEntity("https://data.sparkfun.com/streams/dZ4EVmE8yGCRGx5XRX1W.json",  String.class);
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
}
