package jobApplicationApp.entity;


import javax.persistence.*;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "application")
public class ApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    @Column(name = "date_of_registration")
    private Date dateOfRegistration;

    @OneToOne
    @JoinColumn(name = "status_id")
    private ApplicationStatusEntity status;

    @OneToOne
    @JoinColumn(name = "availability_id")
    private PeriodEntity availableForWork;

    @OneToMany(mappedBy = "application")
    private Collection<CompetenceProfileEntity> competenceProfile;

    public ApplicationEntity(PersonEntity person, Date dateOfRegistration, ApplicationStatusEntity status, PeriodEntity availableForWork, Collection<CompetenceProfileEntity> competenceProfile) {
        this.person = person;
        this.dateOfRegistration = dateOfRegistration;
        this.status = status;
        this.availableForWork = availableForWork;
        this.competenceProfile = competenceProfile;
    }

    /**
     * Set application status
     * @param newStatus  to set on the application
     */
    public void setStatus(ApplicationStatusEntity newStatus) {
        this.status = status;
    }

    /**
     * Get application id
     * @return id of application
     */
    public int getApplicationId(){
        return this.id;
    }

    /**
     * Get person who submitted application
     * @return person who submitted application
     */
    public PersonEntity getPerson(){
        return this.person;
    }

    /**
     * Get date of registration on the application
     * @return date of registration
     */
    public String getDateOfRegistration(){
        return this.dateOfRegistration.toString();
    }

    /**
     * Get current status of the application
     * @return status of the application
     */
    public String getStatus(){
       return this.status.getName();
    }

    /**
     * Get availability period for work
     * @return period that the person is available for work
     */
    public PeriodEntity getAvailability(){
        return this.availableForWork;
    }

    /**
     * Get competence profile
     * @return competenceProfile of the person
     */
    public Collection<CompetenceProfileEntity> getCompetenceProfile(){
        return this.competenceProfile;
    }

}
