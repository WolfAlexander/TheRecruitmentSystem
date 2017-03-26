package edgeapp.fallback;

import com.fasterxml.jackson.databind.ObjectMapper;
import egdeapp.EdgeServiceApplication;
import egdeapp.fallback.AuthServiceFallback;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static org.junit.Assert.assertEquals;


/**
 * Testing Auth Service fallback
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("testing")
@SpringBootTest(classes = EdgeServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthServiceFallbackTest {
    private AuthServiceFallback fallback = new AuthServiceFallback();

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void checkHystrixRoute(){
        assertEquals("auth-service", fallback.getRoute());
    }

    @Test
    public void checkResponseStatusCode() throws IOException {
        assertEquals(HttpStatus.GONE, fallback.fallbackResponse().getStatusCode());
    }

    @Test
    public void checkResponseRawStatusCode() throws IOException {
        assertEquals(410, fallback.fallbackResponse().getRawStatusCode());
    }

    @Test
    public void checkResponseStatusText() throws IOException {
        assertEquals("GONE", fallback.fallbackResponse().getStatusText());
    }

    @Test
    public void checkResponseBody() throws IOException {
        InputStream responseStream = fallback.fallbackResponse().getBody();

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> jsonResponse = mapper.readValue(responseStream, Map.class);

        System.out.println(jsonResponse);

        assertEquals("Authentication service is unavailable!", jsonResponse.get("message"));
    }

    @Test
    public void checkResponseHeaders(){
        HttpHeaders responseHeaders = fallback.fallbackResponse().getHeaders();

        assertEquals(MediaType.APPLICATION_JSON_UTF8, responseHeaders.getContentType());
    }

    @Test
    public void testingFallbackResponse() throws JSONException {
        ResponseEntity<String> response = restTemplate.getForEntity("/auth", String.class);
        //JSONObject jsonResponse = new JSONObject(response.getBody());

       // System.out.println(jsonResponse);
        System.out.println(response);

        assertEquals(HttpStatus.GONE, response.getStatusCode());
        //assertEquals("Authentication service is unavailable!", jsonResponse.get("message"));
    }
}
