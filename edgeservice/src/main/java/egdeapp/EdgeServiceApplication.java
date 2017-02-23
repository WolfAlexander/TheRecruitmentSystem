package egdeapp;

import egdeapp.fallback.AuthServiceFallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * Configuration and startup of Edge Service
 */
@SpringBootApplication
@EnableZuulProxy
@PropertySource("classpath:security/ssl_cred.properties")
public class EdgeServiceApplication{
    /**
     * Creating Hystrix fallback for authentication service
     * @return AuthServiceFallback of type ZuulFallbackProvider
     */
    @Bean
    public ZuulFallbackProvider zuulFallbackProvider(){
        return new AuthServiceFallback();
    }

    /**
     * Main launch method
     * @param args arguments for application
     */
    public static void main(String[] args) {
        SpringApplication.run(EdgeServiceApplication.class, args);
    }
}