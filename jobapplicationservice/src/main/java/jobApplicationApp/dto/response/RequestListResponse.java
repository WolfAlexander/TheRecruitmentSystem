package jobApplicationApp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Collection;

/**
 * Response for when the user needs more then one message
 */
@Getter
@AllArgsConstructor
public class RequestListResponse {

    /**
     * Messages for the user
     * @return the messages
     */
    private Collection<String> messages;
}
