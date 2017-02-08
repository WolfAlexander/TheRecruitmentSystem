package httpResponse;

import org.springframework.http.HttpStatus;

/**
 * This class holds the response that will be sent to the client when trying to register
 * a new user to the recruit system
 *
 * @author Albin Friedner
 *
 */

public class RegistrationResponse
{
    private HttpStatus status;
    private String message;

    public RegistrationResponse(HttpStatus status, String message)
    {
        this.status = status;
        this.message = message;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return String.format("RegistrationResponse status = %s message = %s", status.toString(), message);
    }
}
