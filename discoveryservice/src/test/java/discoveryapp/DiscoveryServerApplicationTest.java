package discoveryapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.BDDAssertions.then;

/**
 * Testing if Eureka service is app and running correctly
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DiscoveryServerApplicationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    /**
     * Requesting all apps known to Eureka
     */
    @Test
    public void testGetApps(){
        ResponseEntity<String> apps = this.testRestTemplate.getForEntity("/eureka/apps", String.class);
        then(apps.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
