package jobApplicationApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "competence")
@NoArgsConstructor
@Getter
public class CompetenceEntity {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String name;

    public CompetenceEntity(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
