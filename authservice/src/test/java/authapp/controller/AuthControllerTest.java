package authapp.controller;

import authapp.AuthServiceLauncher;
import authapp.dto.AuthFailResponse;
import authapp.dto.AuthRequest;
import authapp.dto.AuthTokeResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Testing auth controller
 * Scenario:
 *  1. Test to login with valid credentials
 *  2. Test to login with empty/invalid entered name
 *  3. Test to login with empty/invalid entered password
 *  4. Test to login with invalid credentials
 *  5. Test to access restricted endpoint without authorized access
 *  5. Test to access not-existing endpoint
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("testing")
@SpringBootTest(classes = AuthServiceLauncher.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void loginWithValidCredentials(){
        AuthRequest request = new AuthRequest("user", "password");
        ResponseEntity<AuthTokeResponse> response = this.restTemplate.postForEntity("/auth/login", request, AuthTokeResponse.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(AuthTokeResponse.class, response.getBody().getClass());
    }

    @Test
    public void loginWithInvalidUsername(){
        AuthRequest request = new AuthRequest("", "password");
        ResponseEntity<List> response = this.restTemplate.postForEntity("/auth/login", request, List.class);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void loginWithInvalidPassword(){
        AuthRequest request = new AuthRequest("username", "");
        ResponseEntity<List> response = this.restTemplate.postForEntity("/auth/login", request, List.class);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void loginWithNonExistingUser(){
        AuthRequest request = new AuthRequest("nonuser", "nonpassword");
        ResponseEntity<AuthFailResponse> response = this.restTemplate.postForEntity("/auth/login", request, AuthFailResponse.class);

        assertNotNull(response);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals(AuthFailResponse.class, response.getBody().getClass());
    }

    @Test
    public void accessNonPublicEndpoint(){
        ResponseEntity<String> response = this.restTemplate.getForEntity("/env", String.class);

        assertNotNull(response);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        System.out.println(response);
    }

    @Test
    public void accessNonExitingEndpoint(){
        ResponseEntity<String> response = this.restTemplate.getForEntity("/auth", String.class);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
