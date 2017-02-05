package jobApplicationApp.entity;


import javax.persistence.*;

@Entity
@Table(name = "competence_profile")
public class CompetenceProfileEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private ApplicationEntity application;

    @OneToOne
    @JoinColumn(name = "competence_id")
    private CompetenceEntity competence;

    private int years_of_experience;

    /**
     * Get competence
     * @return competence
     */
    public String getCompetence(){
       return competence.getName();
    }

    /**
     * Get years of experience
     * @return years of experience
     */
    public int getYearsOfExperience(){
       return years_of_experience;
   }
}
