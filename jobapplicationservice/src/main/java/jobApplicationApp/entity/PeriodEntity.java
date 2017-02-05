package jobApplicationApp.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="availability")
public class PeriodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    /**
     * Get from date of period
     * @return fromDate of period
     */
    public String getFrom(){
        return fromDate.toString();
    }

    /**
     * Get to date of period
     * @return toDate of period
     */
    public String getTo(){
        return toDate.toString();
    }
}
