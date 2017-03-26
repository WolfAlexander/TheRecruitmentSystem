package authapp.deserializer;

import authapp.security.JwtUserDetails;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Json Deserializer for JwtUserDetails object
 * Converts Json information to POJO
 */
public class JwtUserDetailsDeserializer extends JsonDeserializer<JwtUserDetails>{
    private final String ID_FIELD_IDENTIFIER = "id";
    private final String USERNAME_FIELD_IDENTIFIER = "username";
    private final String PASSWORD_FIELD_IDENTIFIER = "password";
    private final String AUTHORITIES_FIELD_IDENTIFIER = "authorities";
    private final String ONE_AUTHORITY_FIELD_IDENTIFIER = "authority";

    /**
     * Converts Json to POJO
     * @param jsonParser
     * @param deserializationContext
     * @return
     * @throws IOException
     * @throws JsonProcessingException
     */
    @Override
    public JwtUserDetails deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        Long id = node.get(ID_FIELD_IDENTIFIER).longValue();
        String username = node.get(USERNAME_FIELD_IDENTIFIER).textValue();
        String password = node.get(PASSWORD_FIELD_IDENTIFIER).textValue();
        Collection<GrantedAuthority> grantedAuthorities = getGrantedAuthoritiesFromJSON(node);

        return new JwtUserDetails(id, username, password, grantedAuthorities);
    }

    private Collection<GrantedAuthority> getGrantedAuthoritiesFromJSON(JsonNode node){
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        ArrayNode jsonAuthorities = (ArrayNode) node.get(AUTHORITIES_FIELD_IDENTIFIER);
        jsonAuthorities.forEach(jsonNode -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(jsonNode.get(ONE_AUTHORITY_FIELD_IDENTIFIER).textValue()));
        });

        return grantedAuthorities;
    }
}
