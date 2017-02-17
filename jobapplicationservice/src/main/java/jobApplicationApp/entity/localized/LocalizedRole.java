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
@Table(name = "localized_role")
@IdClass(LocalizedRole.key.class)
public class LocalizedRole {

    @Id
    @NotNull
    private Integer roleId;

    @Id
    @NotNull
    private Integer languageId;

    @NotNull
    private String translation;

    @Getter
    @Setter
    static class key implements Serializable {
        private Integer roleId;
        private Integer languageId;


        // implement equals(), hashcode()
    }

}
