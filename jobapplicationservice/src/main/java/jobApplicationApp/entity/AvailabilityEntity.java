package jobApplicationApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Availability entity
 */
@Entity
@Table(name="availability")
@NoArgsConstructor
@AllArgsConstructor

public class AvailabilityEntity {

    /**
     * The id of the availability object
     * @return the availability's id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     *The start date of availability
     */
    @Column(name = "from_date")
    private Date fromDate;

    /**
     * The end date of availability
     */
    @Column(name = "to_date")
    private Date toDate;

    public AvailabilityEntity(Date fromDate, Date toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     *The start date of availability
     * @return start date of availability
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * The end date of availability
     * @return end date of availability
     */
    public Date getToDate() {
        return toDate;
    }
}
