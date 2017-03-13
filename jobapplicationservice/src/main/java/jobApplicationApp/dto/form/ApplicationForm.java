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
 *
 * @author Adrian Gortzak gortzak@kth.se
 */
@Getter
@AllArgsConstructor
public class ApplicationForm  {

    /**
     * Person id of application
     * @return The person's id of the application.
     */
    @NotNull
    @Min(0)
    private int personId;


    /**
     * The period the applicant can work
     * @return a period where the applicant can work
     */
    @NotNull
    private AvailabilityForm availableForWork;

    /**
     * List of all competences the person provided
     * @return list of competences with years of experience in the specified field
     */
    @NotNull
    private Collection<CompetenceForm> competenceProfile;
}
