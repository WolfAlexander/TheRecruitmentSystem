package jobApplicationApp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Response to the user with one message
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ControllerAdvice
public class RequestResponse {

    /**
     * Message for the user
     * @return the message
     */
    private String message;
}
