package entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Entity class for users of the recruit system that is persisted in an external database.
 *
 * @author Albin Friedner
 */

@Entity
@Getter
@Setter
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    @ManyToOne
    private Role role;
    private String username;
    private String password;


    protected User(){}

    /**
     * All argument constructor for the User class, creates a new user.
     *
     * @param firstName the first name of the user
     * @param lastName  the last name of the user
     * @param dateOfBirth   the social security number of the user
     * @param email the email of the user
     * @param username the username that the user choose
     * @param password the password that the user choose
     */
    public User(String firstName, String lastName, LocalDate dateOfBirth, String email,
                String username, String password)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.username = username;
        this.password = password;
    }


    @Override
    public String toString() {
        return String.format("User, Firstname = %s, Lastname = %s", firstName, lastName);
    }
}