package authapp.config;

import authapp.entity.RoleEntity;
import authapp.entity.UserEntity;
import authapp.profile.Production;
import authapp.repository.RoleRepository;
import authapp.repository.UserRepository;
import authapp.service.UserDetailsRetrieverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 * Application configuration for production
 */
@Production
@Configuration
@SuppressWarnings("SpringJavaAutowiringInspection")
public class ProductionBeanConfig {
    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a template that this service will use to contact other services
     * Rest template is using Ribbon load balancer
     * @return load balanced rest template
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /**
     * Creating user details service implementation that will deliver information about a user
     * @return implementation of user details service
     */
    @Bean(name = "userDetailsService")
    public UserDetailsService userDetailsServiceProduction(){
        return new UserDetailsRetrieverService(restTemplate(), userRepository);
    }

    @Bean
    public CommandLineRunner test(UserRepository userRepository, RoleRepository roleRepository){
        return (args)->{
            RoleEntity userRole = new RoleEntity("ROLE_SERVICE");

            List<RoleEntity> userRoles = new ArrayList<>();
            userRoles.add(userRole);
            roleRepository.save(userRole);

            userRepository.save(new UserEntity("jobapplicationservice", "password", userRoles));

        };
    }
}
