package jobApplicationApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class NoMatchException extends IllegalArgumentException{
    public NoMatchException(String message){
        super(message);
    }
}
