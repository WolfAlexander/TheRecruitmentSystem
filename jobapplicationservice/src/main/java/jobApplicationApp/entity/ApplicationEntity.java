package jobApplicationApp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
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

    public ApplicationEntity(PersonEntity person, Date dateOfRegistration, ApplicationStatusEntity status, AvailabilityEntity availableForWork) {
        this.person = person;
        this.dateOfRegistration = dateOfRegistration;
        this.status = status;
        this.availableForWork = availableForWork;
    }

    /**
     * Set application status
     *
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
