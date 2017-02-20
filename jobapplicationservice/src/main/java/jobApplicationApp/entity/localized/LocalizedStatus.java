package jobApplicationApp.entity.localized;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Translation to application status
 */
@Getter
@Entity
@NoArgsConstructor
@IdClass(LocalizedStatus.key.class)
@Table(name = "localized_status")
public class LocalizedStatus{


    /**
     *Id of the status to translate
     *
     * @return id fo the status to translate
     */
    @Id
    @NotNull
    private Integer statusId;


    /**
     * Id of language to translate to
     *
     * @return id of language
     */
    @Id
    @NotNull
    private Integer languageId;


    /**
     * The translation of the the status to the language specified
     *
     * @return the translation
     */
    @NotNull
    private String translation;


    @Getter
    @Setter
    static class key implements Serializable {
        private Integer statusId;
        private Integer languageId;

    }
}


