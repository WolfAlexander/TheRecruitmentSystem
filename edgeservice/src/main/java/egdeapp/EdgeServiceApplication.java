package egdeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.context.annotation.Bean;

/**
 * Configuration and startup of Edge Service
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class EdgeServiceApplication {
    /**
     * Creating Hystrix fallback for testservice
     * @return TestFallbackProvider of type ZuulFallbackProvider
     */
    @Bean
    public ZuulFallbackProvider zuulFallbackProvider(){
        return new TestFallbackProvider();
    }

    /**
     * Main launch method
     * @param args arguments for application
     */
    public static void main(String[] args) {
        SpringApplication.run(EdgeServiceApplication.class, args);
    }
}
