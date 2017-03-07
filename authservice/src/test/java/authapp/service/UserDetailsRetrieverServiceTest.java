package authapp.service;

import authapp.AuthServiceLauncher;
import authapp.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@ActiveProfiles("testing")
@SpringBootTest(classes = AuthServiceLauncher.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserDetailsRetrieverServiceTest {
    @Autowired
    private UserRepository userRepository;

    private UserDetailsRetrieverService retrieverService = new UserDetailsRetrieverService(new RestTemplate(), userRepository);

    @Test
    public void gettingUserDetailsExistingInLocalDB(){
        try{
            UserDetails userDetails = retrieverService.loadUserByUsername("user");

            assertNotNull(userDetails);
            assertEquals("user", userDetails.getUsername());
            String grantedAuthority = ((GrantedAuthority)userDetails.getAuthorities().toArray()[0]).getAuthority();
            assertEquals("ROLE_USER", grantedAuthority);
        }catch (Exception ex){
            fail();
        }

    }

    @Test
    public void gettingUserDetailsWhenUserServiceIsDown(){
        try {
            UserDetails userDetails = retrieverService.loadUserByUsername("user1");
            fail();
        } catch (ResourceAccessException ex){
            // Successful test
        } catch (Exception ex){
            fail();
        }
    }

    @Test
    public void gettingUnExistingUser(){
        try{
            UserDetails userDetails = retrieverService.loadUserByUsername("notexisting");
            fail();
        }catch (UsernameNotFoundException ex){
            // successful test
        }catch (Exception ex) {
            System.out.println(ex);
            fail();
        }
    }

    @Test
    public void createRequestEntityTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        HttpEntity response = (HttpEntity) invokePrivateMethod(getPrivateMethod("createRequestEntity"));

        assertNotNull(response);
        assertNotNull(response.getHeaders());
    }

    private Method getPrivateMethod(String methodName){
        Method method = null;

        try {
            method = UserDetailsRetrieverService.class.getDeclaredMethod(methodName);
            method.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return method;
    }

    private Object invokePrivateMethod(Method method){
        try {
            return method.invoke(new UserDetailsRetrieverService(new RestTemplate(), userRepository), null);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Test
    public void createRequestHeadersTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        HttpHeaders response = (HttpHeaders) invokePrivateMethod(getPrivateMethod("createRequestHeaders"));

        assertNotNull(response);
        assertNotNull(response.get("Authorization"));
    }

    @Test
    public void  createJwtTokenTest(){
        try {
            String response = (String) invokePrivateMethod(getPrivateMethod("createJwtToken"));
            assertNotNull(response);
        }catch (Exception ex){
            fail();
        }
    }
}
