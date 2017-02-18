package jobApplicationApp.entity.localized;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "languages")
public class LanguageEntity {

    @Id
    private Integer id;

    @NotNull
    private String name;

    public LanguageEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }
}
