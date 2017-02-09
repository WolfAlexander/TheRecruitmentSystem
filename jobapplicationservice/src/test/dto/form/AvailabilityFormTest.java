package dto.form;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AvailabilityFormTest {
    private Date fromDate;
    private Date toDate;

    @Override
    public String toString() {
        return "AvailablePeriod{" + "from:" + fromDate + ", to:" + toDate + '}';
    }
}
