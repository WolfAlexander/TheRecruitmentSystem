package jobApplicationApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class NotValidArgumentException extends IllegalArgumentException{
    public NotValidArgumentException(String message){
        super(message);
    }
}
