package authapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * This object represents a non-successful response to the user
 *
 * @author WolfAlexander nikal@kth.se
 */
@Getter
@AllArgsConstructor
public class AuthFailResponse implements Serializable {
    private String cause;
}
