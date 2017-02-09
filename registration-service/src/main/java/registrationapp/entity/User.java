package registrationapp.entity;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

/**
 * Entity class for users of the recruit system that is persisted in an external database.
 *
 * @author Albin Friedner
 */

@Entity
@Table(name = "person")
@Getter
@Setter
public class User
{
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    private Date dateOfBirth;
    private String email;
    @ManyToOne
    @JoinColumn(name = "role_id")
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
    public User(String firstName, String lastName, Date dateOfBirth, String email,
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