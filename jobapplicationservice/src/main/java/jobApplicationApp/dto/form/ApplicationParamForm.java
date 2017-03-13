package jobApplicationApp.dto.form;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;


/**
 * Form for parameter filtering search
 *
 * @author Adrian Gortzak gortzak@kth.se
 */
@Getter
@AllArgsConstructor
public class ApplicationParamForm {


    /**
     * First name of the user on the application
     * @return required name
     */
    private String name;


    /**
     * Period the applicant should be able to work on
     * return required period
     */
    private AvailabilityForm availableForWork;


    /**
     * The competences the applicant should have
     * @return list of competences
     */
    private Collection<CompetenceForm> competenceProfile;
}
