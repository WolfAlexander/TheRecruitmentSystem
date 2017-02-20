package jobApplicationApp.dto.form;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *  Form for working period
 *  */
@Component
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AvailabilityForm {

    /**
     * The start of the working period
     *
     * @retrun the start date
     */
    private Date fromDate;

    /**
     * The end of the working period
     *
     * @return end date
     */
    private Date toDate;

}
