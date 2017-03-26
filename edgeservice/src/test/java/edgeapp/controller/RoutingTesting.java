package edgeapp.controller;

import com.netflix.zuul.context.RequestContext;
import egdeapp.EdgeServiceApplication;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Integration tests for Routing Testing
 * Testing scenarios:
 * 1. Getting successful response from service
 */

@RunWith(SpringRunner.class)
@ActiveProfiles("testing")
@SpringBootTest(classes = EdgeServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoutingTesting {
    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Setting up a test service that acts as an resource service in production
     */
    @Configuration
    @EnableAutoConfiguration
    @RestController
    static class TestService{
        @GetMapping(value = "testmapping", produces = "application/json")
        public String testMapping(){
            return "I am working!";
        }
    }

    /**
     * Making sure to turn off configuration gathering from config service and ribbon eureka service discovery
     */
    @BeforeClass
    public static void createTestService(){
        System.setProperty("spring.cloud.config.enabled", "false");
        System.setProperty("ribbon.eureka.enabled", "false");
    }

    /**
     * Testing a successful scenario when service is available
     */
    @Test
    public void successfulResponseFromTestService(){
        ConfigurableApplicationContext testService = SpringApplication.run(TestService.class, "--server.port=9876");
        RequestContext.testSetCurrentContext(new RequestContext());

        ResponseEntity<String> response = restTemplate.getForEntity("/test/testmapping", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
        assertEquals("I am working!", response.getBody());

        testService.close();
    }
}