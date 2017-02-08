package inputForm;

import org.hibernate.validator.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * This class backs the registration form created on the client side. It also validates
 * the different fields of the form.
 *
 * @author Albin Friedner
 */

public class RegistrationForm
{
    @NotNull
    @Size(min = 2)
    private String firstName;
    
    @NotNull
    @Size(min = 2)
    private String lastName;
    
    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    private String dateOfBirth;
    
    @NotNull
    @Email
    private String email;
    
    @NotNull
    @Size(min = 2)
    private String username;
    
    @NotNull
    @Size(min = 8)
    private String password;


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
