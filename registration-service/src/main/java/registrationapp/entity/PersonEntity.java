package registrationapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "person")
@NoArgsConstructor
@Getter
public class PersonEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    private String email;

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
     */
    public PersonEntity(String firstName, String lastName, Date dateOfBirth, String email, RoleEntity role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.role = role;
    }





    @Override
    public String toString() {
        return String.format("User, Firstname = %s, Lastname = %s", firstName, lastName);
    }
}
