package jobApplicationApp.entity.localized;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Translation to competence
 */
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(LocalizedCompetence.key.class)
@Table(name = "localized_competence")
public class LocalizedCompetence {


    /**
     *The id of the competence to translate
     *
     *@return competence id of competence to translate
     */
    @Id
    @NotNull
    private int competenceId;

    /**
     * Id of language to translate to
     *
     * @return id of language
     */
    @Id
    @NotNull
    private int languageId;

    /**
     * The translation of the the competence to the language specified
     *
     * @return the translation
     */
    @NotNull
    private String translation;



    @Getter
    @Setter
    static class key implements Serializable {
        private Integer competenceId;
        private Integer languageId;
    }

}
