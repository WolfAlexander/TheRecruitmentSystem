package authapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Custom response to user request for a token
 * This response will be send only if token is created
 *
 * @author WolfAlexander nikal@kth.se
 */
@Getter
@AllArgsConstructor
public class AuthTokeResponse implements Serializable{
    private static final long serialVersionUID = 9104047414186210425L;

    private String token;
}
