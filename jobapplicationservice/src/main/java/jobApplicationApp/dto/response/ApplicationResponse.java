package jobApplicationApp.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jobApplicationApp.dto.form.PersonForm;
import jobApplicationApp.entity.ApplicationEntity;
import jobApplicationApp.entity.ApplicationStatusEntity;
import jobApplicationApp.entity.AvailabilityEntity;
import jobApplicationApp.entity.CompetenceProfileEntity;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;

/**
 * The full application response
 *
 * @author Adrian Gortzak gortzak@kth.se
 */

@Getter
public class ApplicationResponse {

    /**
     * The id of the application
     * @return application id
     */
    private int id;

    /**
     * The user that created the application
     * @return creator of application
     */
    private PersonForm person;

    /**
     * The current status on the application
     * @return application status
     */
    private ApplicationStatusEntity status;

    /**
     * List of all the competences the user provided
     * @return list of competences provided
     */
    private Collection<CompetenceProfileEntity> competenceProfile;

    /**
     * Date the application was made
     * @return date of registration
     */
    private Date dateOfRegistration;

    /**
     * The period the user is available for work
     * @return period user can work
     */
    private AvailabilityEntity AvailableForWork;

    /**
     * Create an application response from application entity and person form
     * @param application to create
     * @param person personal information connected to the application
     */
    public ApplicationResponse(ApplicationEntity application, PersonForm person){
      this.id = application.getId();
      this.status = application.getStatus();
      this.competenceProfile =application.getCompetenceProfile();
      this.dateOfRegistration = application.getDateOfRegistration();
      this.person = person;
      this.AvailableForWork = application.getAvailableForWork();
    }
}
