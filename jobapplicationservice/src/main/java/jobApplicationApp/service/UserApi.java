package jobApplicationApp.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserApi {

    private final RestTemplate restTemplate = new RestTemplate();

    public void getUserById(int id){
        ResponseEntity<String> response = restTemplate.getForEntity("https://data.sparkfun.com/streams/dZ4EVmE8yGCRGx5XRX1W.json",  String.class);
    }

    public boolean validUserId(int id){
        return true;
    }
}
