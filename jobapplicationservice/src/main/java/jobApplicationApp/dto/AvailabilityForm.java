package jobApplicationApp.dto;


import java.util.Date;

public class AvailabilityForm {
    private Date fromDate;
    private Date toDate;

    public AvailabilityForm(){}

    public AvailabilityForm(Date fromDate, Date toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    @Override
    public String toString() {
        return "AvailablePeriod{" +
                "from:" + fromDate +
                ", to:" + toDate +
                '}';
    }
}
