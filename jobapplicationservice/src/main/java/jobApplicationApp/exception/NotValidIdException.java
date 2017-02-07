package jobApplicationApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotValidIdException extends Exception{
    public NotValidIdException(String message){
        super(message);
    }
}
