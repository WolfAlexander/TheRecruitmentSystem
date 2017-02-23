package registrationapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * This is an entity that represents a table for the credentials in the database
 */

@Entity
@Getter
@NoArgsConstructor
@Table(name = "credential")
public class CredentialEntity {

    /**
     * -- GETTER --
     * Get the id of the credentials pair
     *
     * @return The id of the credentials pair
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * -- GETTER --
     * Get the id of the person that has the credentials
     *
     * @return The id of the person that has the credentials
     *
     */
    @NotNull
    private Integer personId;

    /**
     * -- GETTER --
     * Get the username of the person
     *
     * @return The username of the person
     *
     */
    @NotNull
    private String username;

    /**
     * -- GETTER --
     * Get the password of the person
     *
     * @return The password of the person
     *
     */
    @NotNull
    private String password;

    /**
     * Constructor that creates a new CredentialEntity instance based on a person's id,
     * username and password
     *
     * @param personId The id of the person being registered
     * @param username The username of the person being registered
     * @param password The password of the person being registered
     */
    public CredentialEntity(Integer personId, String username, String password) {
        this.personId = personId;
        this.username = username;
        this.password = password;
    }
}
