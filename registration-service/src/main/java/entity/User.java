package entity;

import javax.persistence.*;

/**
 * Entity class for users of the recruit system that is persisted in an external database.
 *
 * @author Albin Friedner
 */

@Entity
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
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
    public User(String firstName, String lastName, String dateOfBirth, String email,
                String username, String password)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.username = username;
        this.password = password;
    }

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

    public void setRole(Role role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
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

    public Role getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return String.format("User, Firstname = %s, Lastname = %s", firstName, lastName);
    }
}