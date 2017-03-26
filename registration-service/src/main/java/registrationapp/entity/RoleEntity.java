package registrationapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * This is an entity that represents a table for a role in the database
 */

@Entity
@Table(name = "role")
@Getter
@NoArgsConstructor
public class RoleEntity {

    /**
     * -- GETTER --
     * Get the id of the role
     *
     * @return The id of the role
     *
     */
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
     private Integer id;

    /**
     * -- GETTER --
     * Get the name of the role
     *
     * @return The name of the role
     *
     */
    @NotNull
    private String name;

    /**
     * A constructor for the RoleEntity that create a new entity based
     * on the name of the role
     *
     * @param name The name of the role
     */
    public RoleEntity(String name) {
        this.name = name;
    }

    /**
     * Set the name of the role
     *
     * @param name The name of the role
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Creates a String representation of the RoleEntity
     *
     * @return A String representation of the RoleEntity
     */
    @Override
    public String toString() {
        return String.format("Role id = %s name = %s", id, name);
    }



}
