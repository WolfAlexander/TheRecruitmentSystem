package jobApplicationApp.entity.localized;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Getter
@Entity
@NoArgsConstructor
@IdClass(LocalizedCompetence.key.class)
@Table(name = "localized_competence")
public class LocalizedCompetence {

    @Id
    @NotNull
    private int competenceId;

    @Id
    @NotNull
    private int languageId;

    @NotNull
    private String translation;

    @Getter
    @Setter
    static class key implements Serializable {
        private Integer competenceId;
        private Integer languageId;

        // implement equals(), hashcode()
    }

}
