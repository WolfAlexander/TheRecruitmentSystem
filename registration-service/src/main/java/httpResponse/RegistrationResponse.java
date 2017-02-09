package httpResponse;

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
    private HttpStatus status;
    private List errorList;

    public RegistrationResponse(HttpStatus status) {
        this.status = status;
    }
}
