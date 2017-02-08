package jobApplicationApp.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import java.util.List;

@Getter
@NoArgsConstructor
class ErrorResponse {

    private HttpStatus status;
    private List errorList;

    public ErrorResponse(HttpStatus status) {
        this.status = status;
    }
    public ErrorResponse(HttpStatus status, List errorList) {
        this.status = status;
        this.errorList = errorList;
    }
}
