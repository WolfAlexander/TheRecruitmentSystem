package jobApplicationApp.dto.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

/**
 * Form for status change
 *
 * @author Adrian Gortzak gortzak@kth.se
 */
@AllArgsConstructor
@Getter
public class ApplicationStatusForm {


    /**
     * New status to be put on the application
     * @return the new status
     */
    @NotNull
    private String name;
}
