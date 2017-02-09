package edgeapp;

import com.netflix.zuul.context.RequestContext;
import egdeapp.EdgeServiceApplication;
import egdeapp.fallback.TestFallbackProvider;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.junit.Assert.assertEquals;

/**
 * Integration tests for EdgeServiceApplication
 * Testing scenarios:
 * 1. Getting successful response from service
 * 2. Getting Hystrix fallback when testservice is unavailable
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EdgeServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EdgeServiceApplicationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    static ConfigurableApplicationContext testService;

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
        System.setProperty("zuul.routes.registration-service.url", "http://localhost:9876");
    }

    /**
     * Launching test service
     */
    @Before
    public void setup() {
        testService = SpringApplication.run(TestService.class, "--server.port=9876");
        RequestContext.testSetCurrentContext(new RequestContext());
    }

    /**
     * Testing a successful scenario when service is available
     */
    @Test
    public void successfulResponseFromTestService(){
        ResponseEntity<String> response = restTemplate.getForEntity("/registration-service/testmapping", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
        assertEquals("I am working!", response.getBody());

    }

    /**
     * Testing scenario where service is unavailable
     */
    @Ignore
    @Test
    public void hystrixFallbackWhenTestServiceUnavailable(){
        testService.close();

        ResponseEntity<String> response = restTemplate.postForEntity("/registration-service/testmapping", null, String.class);
        System.out.println(response);
        assertEquals(HttpStatus.GONE, response.getStatusCode());
    }

    /**
     * Closing test service
     */
    @AfterClass
    public static void close(){
        testService.close();
    }
}