package authapp.service;

import authapp.profiles.Production;
import authapp.security.JwtUserDetails;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.naming.ServiceUnavailableException;

/**
 * This service will request an user service for information about a certain user
 *
 * @author WolfAlexander nikal@kth.se
 */
@Production
@Service
public class UserDetailsRetrieverService implements UserDetailsService{
    @Autowired
    private RestTemplate restTemplate;

    /**
     * Retrieves user information by username
     * @param username - user information will be identified by username
     * @return detailed information about the user
     * @throws UsernameNotFoundException if user with given name not found
     */
    @Override
    @HystrixCommand(fallbackMethod = "userServiceUnavailable")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JwtUserDetails userDetails = restTemplate.getForObject("http://REGISTRATION-SERVICE/user/"+username, JwtUserDetails.class);


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
}
