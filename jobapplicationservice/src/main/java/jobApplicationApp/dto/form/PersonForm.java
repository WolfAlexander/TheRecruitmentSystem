package jobApplicationApp.dto.form;


import lombok.Getter;
import java.util.Date;

/**
 * Form for retrieving user information
 */
@Getter
public class PersonForm {


    /**
     * The first name of the user
     *
     * @return first name
     */
    private String firstName;


    /**
     * The last name of the user
     *
     * @return last name
     */
    private String lastName;


    /**
     * The users date of birth
     *
     * return date of birth
     */
    private Date dateOfBirth;


    /**
     * The users email address
     *
     *
     * @return email address
     */
    private String email;

    /**
     * The role the user have on the site
     *
     *
     * @return The users role
     */
    private RoleForm role;

    /**
     * Constructor without competences though the are connected after an application is created
     *
     * @param firstName
     * @param lastName
     * @param dateOfBirth
     * @param email
     * @param role
     */
    public PersonForm(String firstName, String lastName, Date dateOfBirth, String email, RoleForm role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.role = role;
    }


}
