package jobApplicationApp.dto.form;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/**
 * Form for one competence
 *
 * @author Adrian Gortzak gortzak@kth.se
 */
@Component
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompetenceForm {

    /**
     * Name of the competence
     * @return the name
     */
    @NotNull
    private String name;

    /**
     * years of experience in the field
     * @return years worked in the field
     */
    @NotNull
    @Min(0)
    @Max(120)
    private int yearsOfExperience;

}
