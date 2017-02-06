package jobApplicationApp.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="availability")
public class AvailabilityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    public AvailabilityEntity(){}

    public AvailabilityEntity(Date fromDate, Date toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Get from date of period
     * @return fromDate of period
     */
    public Date getFromDate(){
        return fromDate;
    }

    /**
     * Get to date of period
     * @return toDate of period
     */
    public Date getToDate(){
        return toDate;
    }
}
