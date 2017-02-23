package authapp.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * This object represents a non-successful response to the user
 *
 * @author WolfAlexander nikal@kth.se
 */
@Getter
@Setter
@AllArgsConstructor
public class AuthFailResponse implements Serializable {
    private String cause;
}
