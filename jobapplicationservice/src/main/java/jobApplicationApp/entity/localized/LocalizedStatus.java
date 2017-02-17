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
@IdClass(LocalizedStatus.key.class)
@Table(name = "localized_status")
public class LocalizedStatus{

    @Id
    @NotNull
    private Integer statusId;

    @Id
    @NotNull
    private Integer languageId;

    @NotNull
    private String translation;

    @Getter
    @Setter
    static class key implements Serializable {
        private Integer statusId;
        private Integer languageId;

        // implement equals(), hashcode()
    }
}


