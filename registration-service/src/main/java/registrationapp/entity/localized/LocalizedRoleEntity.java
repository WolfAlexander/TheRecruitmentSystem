package registrationapp.entity.localized;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * This is an entity that represents a table for a translation in the database
 */

@Getter
@Entity
@NoArgsConstructor
@Table(name = "localized_role")
@IdClass(LocalizedRoleEntity.key.class)
public class LocalizedRoleEntity {

    /**
     * -- GETTER --
     * Get the id of the role that is being translated
     *
     * @return The id of the role that is being translated
     *
     */
    @Id
    @NotNull
    private Integer roleId;

    /**
     * -- GETTER --
     * Get the language that the role is being translated to
     *
     * @return The language that the role is being translated to
     *
     */
    @Id
    @NotNull
    private Integer languageId;

    /**
     * -- GETTER --
     * Get the translation of the role to the language described
     *
     * @return The translation of the role to the language described
     *
     */
    @NotNull
    private String translation;

    //TODO: Make javadoc for this inner class
    @Getter
    @Setter
    static class key implements Serializable {
        private Integer roleId;
        private Integer languageId;


        //TODO: Implement equals(), hashcode()
    }

}
