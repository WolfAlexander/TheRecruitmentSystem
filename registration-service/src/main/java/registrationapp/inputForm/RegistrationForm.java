package registrationapp.inputForm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    /**
     * -- SETTER --
     * Set the first name of the user
     *
     * -- GETTER --
     * Get the first name of the user
     *
     * @param firstname The first name of the user
     * @return The first name of the user
     *
     */
    @NotNull
    @Size(min = 2)
    private String firstname;

    /**
     * -- SETTER --
     * Set the last name of the user
     *
     * -- GETTER --
     * Get the last name of the user
     *
     * @param lastname The last name of the user
     * @return The last name of the user
     *
     */
    @NotNull
    @Size(min = 3)
    private String lastname;

    /**
     * -- SETTER --
     * Set the date the user was born
     *
     * -- GETTER --
     * Get the date the user was born
     *
     * @param dateOfBirth The date the user was born
     * @return The date the user was born
     *
     */
    @NotNull
    private Date dateOfBirth;

    /**
     * -- SETTER --
     * Set the email of the user
     *
     * -- GETTER --
     * Get the email of the user
     *
     * @param email The email of the user
     * @return The email of the user
     *
     */
    @NotNull
    @Email
    private String email;

    /**
     * -- SETTER --
     * Set the username of the user
     *
     * -- GETTER --
     * Get the username of the user
     *
     * @param username The username of the user
     * @return The username of the user
     *
     */
    @NotNull
    @Size(min = 2)
    private String username;

    /**
     * -- SETTER --
     * Set the password of the user
     *
     * -- GETTER --
     * Get the password of the user
     *
     * @param password The password of the user
     * @return The password of the user
     *
     */
    @NotNull
    @Size(min = 8)
    private String password;
}
