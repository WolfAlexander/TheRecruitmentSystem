package edgeapp;

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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.junit.Assert.assertEquals;

/**
 * JUnit tests for EdgeServiceApplication
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

    @Configuration
    @EnableAutoConfiguration
    @RestController
    static class TestService{
        @GetMapping(value = "testmapping", produces = "application/json")
        public String testMapping(){
            return "I am working!";
        }
    }

    @BeforeClass
    public static void createTestService(){
        System.setProperty("spring.cloud.config.enabled", "false");
        System.setProperty("ribbon.eureka.enabled", "false");
        System.setProperty("zuul.routes.test.url", "http://localhost:9876");
        testService = SpringApplication.run(TestService.class, "--server.port=9876");
    }

    @Before
    public void setup() {
        RequestContext.testSetCurrentContext(new RequestContext());
    }

    @Test
    public void successfulResponseFromTestService(){
        ResponseEntity<String> response = restTemplate.getForEntity("/test/testmapping", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
        assertEquals("I am working!", response.getBody());

    }

    @Test
    public void hystrixFallbackWhenTestServiceUnavailable(){
    }

    @AfterClass
    public static void close(){
        testService.close();
    }
}