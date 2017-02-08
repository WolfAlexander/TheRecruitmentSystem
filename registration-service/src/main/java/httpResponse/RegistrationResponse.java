package httpResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponse{
    private HttpStatus status;
    private List errorList;

    public RegistrationResponse(HttpStatus status) {
        this.status = status;
    }
}
