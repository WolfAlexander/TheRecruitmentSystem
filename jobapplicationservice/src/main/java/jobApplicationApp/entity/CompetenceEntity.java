package jobApplicationApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Competence entity
 */
@Entity
@Table(name = "competence")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CompetenceEntity {


    /**
     * The id of the competence
     * @return the competence's id
     */
    @Id
    @JsonIgnore
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;


    /**
     * The name of the competence
     * @return competence name
     */
    @NotNull
    private String name;

    /**
     * Set the name of competence
     * @param name is the new name of the competence
     */
    public void setName(String name) {
        this.name = name;
    }


}
