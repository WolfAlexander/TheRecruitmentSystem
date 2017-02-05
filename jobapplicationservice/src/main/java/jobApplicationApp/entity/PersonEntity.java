package jobApplicationApp.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "person")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String firstname;
    private String lastname;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    private String email;

    @OneToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    /**
     * Get first name of person
     * @return firstName of person
     */
    public String getFirstname(){
        return this.firstname;
    }

    /**
     * Get last name of person
     * @return lastName of person
     */
    public String getLastname(){
        return this.lastname;
    }

    /**
     * Get date of birth
     * @return dateOfBirth of person
     */
    public String getDateOfBirth(){
        return this.dateOfBirth.toString();
    }

    /**
     * Get email address to person
     * @return email address to person
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Get role of person
     * @return role of person
     */
    public String getRole(){
        return this.role.getRole();
    }
}
