package jobApplicationApp.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Competence profile entity
 */
@Entity
@Table(name = "competence_profile")
@NoArgsConstructor
public class CompetenceProfileEntity {

    /**
     * The id of the competence profile
     */
    @Id
    @JsonIgnore
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    /**
     * The application the profile is connected to
     */
    @ManyToOne
    @JsonIgnore
    private ApplicationEntity application;

    /**
     *The competence the profile contains
     * @return the competence object
     */
    @Getter
    @OneToOne
    @JoinColumn(name = "competence_id")
    private CompetenceEntity competence;

    /**
     * The years of experience in the field
     * @return the years of experience
     */
    private int yearsOfExperience;

    public CompetenceProfileEntity(ApplicationEntity application, CompetenceEntity competence, int years_of_experience) {
        this.application = application;
        this.competence = competence;
        this.yearsOfExperience = years_of_experience;
    }

    public CompetenceProfileEntity(CompetenceEntity competence, int yearsOfExperience) {
        this.competence = competence;
        this.yearsOfExperience = yearsOfExperience;
    }
}
