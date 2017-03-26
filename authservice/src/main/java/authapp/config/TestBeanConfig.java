package authapp.config;

import authapp.entity.RoleEntity;
import authapp.entity.UserEntity;
import authapp.profile.ForTesting;
import authapp.repository.RoleRepository;
import authapp.repository.UserRepository;
import authapp.security.JwtUserDetails;
import authapp.service.UserDetailsRetrieverService;
import authapp.service.UserDetailsServiceTestImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.ribbon.proxy.annotation.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Application configuration for testing
 */
@ForTesting
@Configuration
@SuppressWarnings("SpringJavaAutowiringInspection")
public class TestBeanConfig {
    @Autowired
    private UserRepository repository;

    /**
     * Create testing embedded db information
     * @param userRepository
     * @param roleRepository
     * @return
     */
    @Bean
    public CommandLineRunner initDB(UserRepository userRepository, RoleRepository roleRepository){
        return (args) ->{
            RoleEntity userRole = new RoleEntity("ROLE_USER");
            RoleEntity adminRole = new RoleEntity("ROLE_ADMIN");

            List<RoleEntity> userRoles = new ArrayList<>();
            userRoles.add(userRole);

            List<RoleEntity> adminRoles = new ArrayList<>();
            adminRoles.add(adminRole);
            adminRoles.add(userRole);

            roleRepository.save(userRole);
            roleRepository.save(adminRole);

            userRepository.save(new UserEntity("user", "password", userRoles));
            userRepository.save(new UserEntity("admin", "password", adminRoles));
            UserEntity user = userRepository.findByUsername("user");

            StreamSupport.stream(userRepository.findAll().spliterator(), false).forEach(System.out::println);
            System.out.println("FindByUsername: " + user);
        };
    }

    /**
     * Creating test user details service implementation that will deliver information about a user
     * @return implementation of test user details service
     */
    @Bean(name = "userDetailsService")
    public UserDetailsService userDetailsServiceForTesting(){
        return new UserDetailsServiceTestImpl();
    }

    @Bean(name = "userDetailsRetriverForTesting")
    public UserDetailsService userDetailsService() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        MockRestServiceServer registerService = MockRestServiceServer.bindTo(restTemplate).build();

        ObjectMapper mapper = new ObjectMapper();
        JwtUserDetails testDetails = new JwtUserDetails(1L, "user", "password", new SimpleGrantedAuthority("ROLE_USER"));

        registerService.expect(
                ExpectedCount.manyTimes(),
                requestTo("http://REGISTRATION-SERVICE/get/usercredentials/by/realuser")).andExpect(method(HttpMethod.GET)).andRespond(withSuccess(mapper.writeValueAsString(testDetails), MediaType.APPLICATION_JSON));

        return new UserDetailsRetrieverService(new RestTemplate(), repository);
    }
}
