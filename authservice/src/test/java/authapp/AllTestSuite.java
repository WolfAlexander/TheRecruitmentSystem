package authapp;

import authapp.controller.AuthController;
import authapp.controller.AuthControllerTest;
import authapp.deserializer.JwtUserDetailsDeserializerTest;
import authapp.security.JwtUserDetailsTest;
import authapp.service.UserDetailsRetrieverServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AuthControllerTest.class,
        JwtUserDetailsTest.class,
        UserDetailsRetrieverServiceTest.class,
        JwtUserDetailsDeserializerTest.class
})
public class AllTestSuite {
}
