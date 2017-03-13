package jobApplicationApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception for when key can't be matched with something in the database
 *
 * @author Adrian Gortzak gortzak@kth.se
 */
public class NoMatchException extends IllegalArgumentException{


    /**
     * Error message
     * @param message to send to the user
     */
    public NoMatchException(String message){
        super(message);
    }
}
