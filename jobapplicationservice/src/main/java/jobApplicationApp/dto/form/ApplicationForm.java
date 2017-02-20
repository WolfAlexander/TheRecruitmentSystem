package jobApplicationApp.dto.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * Form for retrieving application
 */
@Component
@Getter
@NoArgsConstructor
public class ApplicationForm {

    /**
     * Person id of application
     *
     * @return The persons id of the application.
     */
    @NotNull
    @Min(0)
    private int personId;


    /**
     * The period they applicant can work
     *
     * @return a period where the applicant can work
     */
    @NotNull
    private AvailabilityForm availableForWork;

    /**
     * List of all competence the person provided
     * @return list of competences with years of experience in the specified field
     */
    @NotNull
    private Collection<CompetenceForm> competenceProfile;

    /**
     * todo
     *
     * @param personId of the creater of the application
     * @param availableForWork period that the user can work
     * @param competenceProfile list of competences the user provided
     */
    public ApplicationForm(Integer personId, AvailabilityForm availableForWork, Collection<CompetenceForm> competenceProfile) {
        this.personId = personId;
        this.availableForWork = availableForWork;
        this.competenceProfile = competenceProfile;
    }
}
