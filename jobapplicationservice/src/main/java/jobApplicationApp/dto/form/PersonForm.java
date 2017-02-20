package jobApplicationApp.dto.form;


import lombok.Getter;
import java.util.Date;

@Getter
public class PersonForm {

    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String email;
    private RoleForm role;

    public PersonForm(String firstName, String lastName, Date dateOfBirth, String email, RoleForm role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.role = role;
    }


}
