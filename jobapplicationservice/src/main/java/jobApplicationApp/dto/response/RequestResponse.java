package jobApplicationApp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ControllerAdvice
public class RequestResponse {
    private String message;
}
