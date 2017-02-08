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
    private AvailabilityEntity availableForWork;

    @OneToMany(mappedBy = "application")
    private Collection<CompetenceProfileEntity> competenceProfile;

    public ApplicationEntity(){}

    public ApplicationEntity(PersonEntity person, Date dateOfRegistration, ApplicationStatusEntity status, AvailabilityEntity availableForWork, Collection<CompetenceProfileEntity> competenceProfile) {
        this.person = person;
        this.dateOfRegistration = dateOfRegistration;
        this.status = status;
        this.competenceProfile = competenceProfile;
        this.availableForWork = availableForWork;
    }


    public ApplicationEntity(PersonEntity person, Date dateOfRegistration, ApplicationStatusEntity status, AvailabilityEntity availableForWork) {
        this.person = person;
        this.dateOfRegistration = dateOfRegistration;
        this.status = status;
        this.availableForWork = availableForWork;
    }

    /**
     * Set application status
     * @param newStatus  to set on the application
     */

    public void changeStatus(ApplicationStatusEntity newStatus) {
        this.status = newStatus;
    }


    /**
     * Get application id
     * @return id of application
     */
    public int getId(){
        if(this.id == null)
            return -1;
        else
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
    public Date getDateOfRegistration(){
        return this.dateOfRegistration;
    }

    /**
     * Get current status of the application
     * @return status of the application
     */
    public ApplicationStatusEntity getStatus(){
       return this.status;
    }

    /**
     * Get availability period for work
     * @return period that the person is available for work
     */
    public AvailabilityEntity getAvailableForWork(){
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
