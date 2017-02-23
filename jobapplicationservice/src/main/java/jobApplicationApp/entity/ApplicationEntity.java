package jobApplicationApp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

/**
 * Application entity
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name = "application")
public class ApplicationEntity {


    /**
     * Id of the application
     *@return  id of application
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    /**
     * The id of the person that created the application
     * @return the persons id
     */
    @NotNull
    private int personId;

    /**
     * The date the application was registered
     * @return date of registration
     */
    @Column(name = "date_of_registration")
    private Date dateOfRegistration;


    /**
     * The current status of the application
     * @return status of application
     */
    @OneToOne
    @JoinColumn(name = "status_id")
    private ApplicationStatusEntity status;


    /**
     * Period the user is available for work
     * @return availability period
     */
    @OneToOne
    @JoinColumn(name = "availability_id")
    private AvailabilityEntity availableForWork;


    /**
     * List of competences the user has provided
     * @return competences of user
     */
    @OneToMany(mappedBy = "application")
    private Collection<CompetenceProfileEntity> competenceProfile;

    /**
     * Create Application
     * @param personId id of the creator
     * @param dateOfRegistration of the application
     * @param status of the application
     * @param availableForWork period that the person can work
     * @param competenceProfile list of competences
     */
    public ApplicationEntity(int personId, Date dateOfRegistration, ApplicationStatusEntity status, AvailabilityEntity availableForWork, Collection<CompetenceProfileEntity> competenceProfile) {
        this.personId = personId;
        this.dateOfRegistration = dateOfRegistration;
        this.status = status;
        this.availableForWork = availableForWork;
        this.competenceProfile = competenceProfile;
    }

    /**
     *  Create Application
     * @param personId id of the creator
     * @param dateOfRegistration of the application
     * @param status of the application
     * @param availableForWork period that the person can work
     */
    public ApplicationEntity(int personId, Date dateOfRegistration, ApplicationStatusEntity status, AvailabilityEntity availableForWork) {
        this.personId = personId;
        this.dateOfRegistration = dateOfRegistration;
        this.status = status;
        this.availableForWork = availableForWork;
    }

    /**
     * Set application status
     * @param newStatus to set on the application
     */
    public void changeStatus(ApplicationStatusEntity newStatus) {
        this.status = newStatus;
    }

    /**
     * Get application id
     * @return id of application
     */
    public int getId() {
        if (this.id == null)
            return -1;
        else
            return this.id;
    }
}
