package jobApplicationApp.entity.localized;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Translation to application status
 *
 * @author Adrian Gortzak gortzak@kth.se
 */
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(LocalizedStatus.key.class)
@Table(name = "localized_status")
public class LocalizedStatus{


    /**
     *Id of the status to translate
     * @return id fo the status to translate
     */
    @Id
    @NotNull
    private Integer statusId;


    /**
     * Id of language to translate to
     * @return id of language
     */
    @Id
    @NotNull
    private Integer languageId;


    /**
     * The translation of the the status to the language specified
     * @return the translation
     */
    @NotNull
    private String translation;


    @Getter
    @Setter
    static class key implements Serializable {
        private Integer statusId;
        private Integer languageId;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            key key = (key) o;

            if (statusId != null ? !statusId.equals(key.statusId) : key.statusId != null) return false;
            return languageId != null ? languageId.equals(key.languageId) : key.languageId == null;
        }

        @Override
        public int hashCode() {
            int result = statusId != null ? statusId.hashCode() : 0;
            result = 31 * result + (languageId != null ? languageId.hashCode() : 0);
            return result;
        }
    }
}


