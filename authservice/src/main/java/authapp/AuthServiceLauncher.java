package authapp;

import authapp.entity.RoleEntity;
import authapp.entity.UserEntity;
import authapp.repository.RoleRepository;
import authapp.repository.UserRepository;
import authapp.service.UserDetailsRetrieverService;
import authapp.service.UserDetailsServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 * Class that starts authentication service
 */
@SpringBootApplication
@EnableEurekaClient
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthServiceLauncher {
    public static void main(String[] args) {
        SpringApplication.run(AuthServiceLauncher.class, args);
    }

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
     * Creating user details service implementation
     * @return implementation of user details service
     */
    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }
}
