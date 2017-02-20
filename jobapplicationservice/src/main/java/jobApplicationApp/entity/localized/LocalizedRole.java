package jobApplicationApp.entity.localized;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * Translation to role
 */
@Getter
@Entity
@NoArgsConstructor
@Table(name = "localized_role")
@IdClass(LocalizedRole.key.class)
public class LocalizedRole {


    /**
     *Id of the role to translate
     *
     * @return id fo the role to translate
     */
    @Id
    @NotNull
    private Integer roleId;


    /**
     * Id of language to translate to
     *
     * @return id of language
     */
    @Id
    @NotNull
    private Integer languageId;


    /**
     * The translation of the the role to the language specified
     *
     * @return the translation
     */
    @NotNull
    private String translation;

    @Getter
    @Setter
    static class key implements Serializable {
        private Integer roleId;
        private Integer languageId;

    }

}
