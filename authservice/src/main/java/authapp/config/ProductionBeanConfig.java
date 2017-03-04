package authapp.config;

import authapp.profiles.Production;
import authapp.service.UserDetailsRetrieverService;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.client.RestTemplate;

/**
 * Application configuration for production
 */
@Production
@Configuration
public class ProductionBeanConfig {
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
        return new UserDetailsRetrieverService(restTemplate());
    }
}
