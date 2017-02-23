package registrationapp.entity.localized;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * This is an entity that represents a table for a language in the database
 *
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "languages")
public class LanguageEntity {

    @Id
    private Integer id;

    @NotNull
    private String name;

    /**
     * A constructor for creating a new LanguageEntity instance based
     * on the name of the language
     *
     * @param name The name of the language
     */
    public LanguageEntity(String name) {
        this.name = name;
    }

    /**
     * Get the name of the language
     *
     * @return The name of the language
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Get the id of the LanguageEntity instance
     *
     * @return The id of the LanguageEntity instance
     */
    public Integer getId() {
        return id;
    }
}
