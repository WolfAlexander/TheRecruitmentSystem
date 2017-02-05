package jobApplicationApp.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "competence")
public class CompetenceEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String name;

    /**
     * Get name of competence
     * @return name of competence
     */
    public String getName() {
        return name;
    }
}
