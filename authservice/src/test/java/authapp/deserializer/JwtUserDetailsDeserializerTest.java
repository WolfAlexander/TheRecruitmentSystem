package authapp.deserializer;

import authapp.security.JwtUserDetails;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Testing deserialization of JwtUserDetails
 * Scenario:
 * 1. Valid serialized object becomes valid object of deserialization
 */
public class JwtUserDetailsDeserializerTest {
    private ObjectMapper mapper = new ObjectMapper();
    private JwtUserDetailsDeserializer deserializer = new JwtUserDetailsDeserializer();

    @Test
    public void deserializeTest() throws IOException {
        JwtUserDetails userDetails = new JwtUserDetails(0L, "user", "password", new SimpleGrantedAuthority("TESTER"));
        String serialized = mapper.writeValueAsString(userDetails);

        JsonParser parser = mapper.getFactory().createParser(serialized);
        DeserializationContext context = mapper.getDeserializationContext();

        System.out.println(serialized);

        JwtUserDetails deserialized = deserializer.deserialize(parser, context);

        assertNotNull(deserialized);
        assertEquals("user", deserialized.getUsername());
        assertEquals("password", deserialized.getPassword());
        assertNotNull(deserialized.getAuthorities().contains(new SimpleGrantedAuthority("TESTER")));
    }
}
