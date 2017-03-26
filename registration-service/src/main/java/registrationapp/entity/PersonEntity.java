package registrationapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * This is an entity that represents a table for a user in the database
 */

@Entity
@Table(name = "person")
@NoArgsConstructor
@Getter
public class PersonEntity {

    /**
     * -- GETTER --
     * Get the id of the person
     *
     * @return The id of the person
     *
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    /**
     * -- GETTER --
     * Get the first name of the person
     *
     * @return The first name of the person
     *
     */
    @Column(name = "firstname")
    private String firstName;

    /**
     * -- GETTER --
     * Get the last name of the person
     *
     * @return The last name of the person
     *
     */
    @Column(name = "lastname")
    private String lastName;

    /**
     * -- GETTER --
     * Get the date the person was born
     *
     * @return The date the person was born
     *
     */
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    /**
     * -- GETTER --
     * Get the email of the person
     *
     * @return The email of the person
     *
     */
    private String email;

    /**
     * -- GETTER --
     * Get the person's role
     *
     * @return The person's role
     *
     */
    @OneToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    /**
     * All argument constructor for the User class, creates a new user.
     *
     * @param firstName the first name of the user
     * @param lastName  the last name of the user
     * @param dateOfBirth   the social security number of the user
     * @param email the email of the user
     * @param role the role that the user possess
     */
    public PersonEntity(String firstName, String lastName, Date dateOfBirth, String email, RoleEntity role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.role = role;
    }

    /**
     * Get a String representation of the PersonEntity
     *
     * @return A String representation of the PersonEntity
     */
    @Override
    public String toString() {
        return String.format("User, Firstname = %s, Lastname = %s", firstName, lastName);
    }
}
