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

@Service
public class UserApi {

    private final RestTemplate restTemplate = new RestTemplate();

    public PersonForm getUserById(int id){

        Date dateOfRegistration =null;
        try {
            dateOfRegistration = new SimpleDateFormat("yyyy-MM-dd").parse("2016-02-17");
        }catch (Exception e){

        }
        return new PersonForm("Adrian","Gortzak",dateOfRegistration,"addegor@hotmail.com",new RoleForm("test"));
        //ResponseEntity<String> response = restTemplate.getForEntity("https://data.sparkfun.com/streams/dZ4EVmE8yGCRGx5XRX1W.json",  String.class);
    }

    public Collection<Integer> getIdOfUsersWithName(String name){
        ArrayList<Integer> test = new ArrayList<>();
        test.add(3);
        test.add(5);
        return test;
    }

    public boolean validUserId(int id){
        return true;
    }
}
