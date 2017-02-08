package jobApplicationApp.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@NoArgsConstructor
public class RequestResponse {

    private HttpStatus status;
    private String message;

    public RequestResponse(HttpStatus status) {
        this.status = status;
    }
    public RequestResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
