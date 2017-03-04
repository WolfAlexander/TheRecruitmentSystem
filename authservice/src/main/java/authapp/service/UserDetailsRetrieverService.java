package authapp.service;

import authapp.profiles.Production;
import authapp.security.JwtUserDetails;
import authapp.security.RSAJwtTokenFactory;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.naming.ServiceUnavailableException;
import javax.swing.text.html.HTMLWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * This service will request an user service for information about a certain user
 *
 * @author WolfAlexander nikal@kth.se
 */
@Production
@Service
public class UserDetailsRetrieverService implements UserDetailsService{
    private final String SERVICE_NAME = "REGISTRATION-SERVICE";
    private final RestTemplate restTemplate;

    /**
     * Autowiring RestTemplate instance
     * @param restTemplate - RestTemplate instance
     */
    @Autowired
    public UserDetailsRetrieverService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Retrieves user information by username
     * @param username - user information will be identified by username
     * @return detailed information about the user
     * @throws UsernameNotFoundException if user with given name not found
     */
    @Override
    @HystrixCommand(fallbackMethod = "userServiceUnavailable")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JwtUserDetails userDetails = getUserDetailsByName(username);

        if(userDetails == null)
            throw new UsernameNotFoundException("No user found with given username: " + username);
        else
            return userDetails;
    }

    /**
     * Fallback method when user service is not available
     * @throws ServiceUnavailableException with message that service is unavailable
     */
    public void userServiceUnavailable() throws ServiceUnavailableException {
        throw new ServiceUnavailableException("Service unavailable");
    }

    private JwtUserDetails getUserDetailsByName(String username){
        return restTemplate.exchange("http://REGISTRATION-SERVICE/get/usercredentials/by/"+username, HttpMethod.GET, createRequestEntity(), JwtUserDetails.class).getBody();
    }

    private HttpEntity createRequestEntity(){
        return new HttpEntity<>(createRequestHeaders());
    }

    private HttpHeaders createRequestHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", createJwtToken());

        return headers;
    }

    private String createJwtToken(){
        JwtUserDetails serviceDetails = new JwtUserDetails(0L, "auth_service", null, new SimpleGrantedAuthority("ROLE_SERVICE"));
        return RSAJwtTokenFactory.generateTokenForAUser(serviceDetails);
    }
}