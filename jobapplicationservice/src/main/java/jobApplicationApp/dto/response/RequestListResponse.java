package jobApplicationApp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Collection;

@Getter
@AllArgsConstructor
public class RequestListResponse {
    private HttpStatus status;
    private Collection<String> messages;
}
