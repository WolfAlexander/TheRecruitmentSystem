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


@Getter
@Entity
@NoArgsConstructor
@Table(name = "localized_role")
@IdClass(LocalizedRoleEntity.key.class)
public class LocalizedRoleEntity {

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
