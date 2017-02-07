package jobApplicationApp.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

public class ApplicationForm {

  @NotNull
  @Min(0)
  Integer personId;

  @NotNull
  Date availableForWorkFrom;

  @NotNull
  Date availableForWorkTo;

  Collection<CompetenceForm> competences;

    public ApplicationForm(){};

    public ApplicationForm(Integer personId, Date availableForWorkFrom, Date availableForWorkTo, Collection<CompetenceForm> competences) {
        this.personId = personId;
        this.availableForWorkFrom = availableForWorkFrom;
        this.availableForWorkTo = availableForWorkTo;
        this.competences = competences;
    }

    public Integer getPersonId() {
        return personId;
    }

    public Date getAvailableForWorkFrom() {
        return availableForWorkFrom;
    }

    public Date getAvailableForWorkTo() {
        return availableForWorkTo;
    }

    public Collection<CompetenceForm> getCompetences() {
        return competences;
    }
}
