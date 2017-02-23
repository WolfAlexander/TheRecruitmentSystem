package registrationapp.httpResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * This class encapsulates a response on a HTTP request. The response that is being converted to JSON is
 * providing the client with information on how the registration went.
 *
 * @author Albin Friedner
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponse{

    /**
     * -- SETTER --
     * Set the HTTP Status of the response
     *
     * -- GETTER --
     * Get the HTTP Status of the response
     *
     * @param status The HTTP Status in the response
     * @return The HTTP Status in the response
     *
     */
    private HttpStatus status;

    /**
     * -- SETTER --
     * Set the list of errors displayed in the response
     *
     * -- GETTER --
     * Get the list of errors displayed in the response
     *
     * @param errorList A List of errors that was encountered during the registration of a user
     * @return A List of errors that was encountered during the registration of a user
     *
     */
    private List errorList;

    /**
     * Constructor that creates a RegistrationResponse object with a HTTP Status only
     *
     * @param status The HTTP Status that is going to be in the response.
     *
     */
    public RegistrationResponse(HttpStatus status) {
        this.status = status;
    }
}
