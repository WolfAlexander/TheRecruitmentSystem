package authapp.config;

import authapp.entity.RoleEntity;
import authapp.entity.UserEntity;
import authapp.profile.ForTesting;
import authapp.repository.RoleRepository;
import authapp.repository.UserRepository;
import authapp.service.UserDetailsServiceTestImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 * Application configuration for testing
 */
@ForTesting
@Configuration
@SuppressWarnings("SpringJavaAutowiringInspection")
public class TestBeanConfig {
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
}
