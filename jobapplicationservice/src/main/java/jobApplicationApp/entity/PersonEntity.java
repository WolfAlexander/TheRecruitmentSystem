package jobApplicationApp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "person")
@NoArgsConstructor
@Getter
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

    public PersonEntity(String firstname, String lastname, Date dateOfBirth, String email, RoleEntity role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.role = role;
    }
}
