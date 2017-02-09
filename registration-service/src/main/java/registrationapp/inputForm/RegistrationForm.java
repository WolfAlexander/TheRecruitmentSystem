package registrationapp.inputForm;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

/**
 * This class backs the registration form created on the client side. It also validates
 * the different fields of the form.
 *
 * @author Albin Friedner
 */

@Getter
@Setter
@NoArgsConstructor
public class RegistrationForm
{
    @NotNull
    @Size(min = 2)
    private String firstname;

    @NotNull
    @Size(min = 3)
    private String lastname;
    /*
    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;
*/
    @NotNull
    //@JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @NotNull
    @Email
    private String email;
    
    @NotNull
    @Size(min = 2)
    private String username;
    
    @NotNull
    @Size(min = 8)
    private String password;
}
