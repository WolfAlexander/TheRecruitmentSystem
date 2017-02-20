package jobApplicationApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Exception for invalid parameters from the user
 */
public class NotValidArgumentException extends IllegalArgumentException{
    /**
     * Error message
     * @param message to send to user
     */
    public NotValidArgumentException(String message){
        super(message);
    }
}
