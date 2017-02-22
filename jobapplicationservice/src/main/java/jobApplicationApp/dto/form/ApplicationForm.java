package jobApplicationApp.dto.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

/**
 * Form for retrieving application
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationForm  {

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


}
