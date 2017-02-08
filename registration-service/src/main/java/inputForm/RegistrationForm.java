package inputForm;

import javax.validation.constraints.NotNull;

/**
 * This class backs the registration form created on the client side. It also validates
 * the different fields of the form.
 *
 * @author Albin Friedner
 */

public class RegistrationForm
{
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String dateOfBirth;
    @NotNull
    private String email;
    @NotNull
    private String username;
    @NotNull
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
