package edgeapp.controller;


import egdeapp.EdgeServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Testing message bundles for different language
 * Scenarios:
 * 1. Test get message bundle for swedish language
 * 2. Test getting message bundle for english language
 * 3. Test getting message bundle for non-existing language
 * 4. Test getting message without required lang parameter
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("testing")
@SpringBootTest(classes = EdgeServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LanguageEndpointTesting {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getSwedishLanguageMessageBundle(){
        Properties properties = this.restTemplate.getForObject("/messageBundle?lang={lang}", Properties.class, "sv");

        assertNotNull(properties);
        assertEquals("Recruterings system", properties.getProperty("homepage.title"));
    }

    @Test
    public void getEnglishLanguageMessageBundle(){
        Properties properties = this.restTemplate.getForObject("/messageBundle?lang={lang}", Properties.class, "en");

        assertNotNull(properties);
        assertEquals("The recruitment system", properties.getProperty("homepage.title"));
    }

    @Test
    public void getLanguageMessageBundleForNonExistingLanguage(){
        Properties properties = this.restTemplate.getForObject("/messageBundle?lang={lang}", Properties.class, "fi");

        assertNotNull(properties);
        assertEquals("The recruitment system", properties.getProperty("homepage.title"));
    }

    @Test
    public void tryToGetMessageBundleWithoutLangParam(){
        ResponseEntity<Properties> response = this.restTemplate.getForEntity("/messageBundle", Properties.class, "sv");

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
