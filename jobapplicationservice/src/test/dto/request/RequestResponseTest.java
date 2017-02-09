package dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ControllerAdvice
public class RequestResponseTest {
    private String message;
}
