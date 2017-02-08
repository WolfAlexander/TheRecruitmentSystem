package jobApplicationApp.dto;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Component
public class ApplicationForm {

  @NotNull
  @Min(0)
  Integer personId;

  @NotNull
  AvailabilityForm availableForWork;

  @NotNull
  Collection<CompetenceForm> competenceProfile;

    public ApplicationForm(){};

    public ApplicationForm(Integer personId, AvailabilityForm availableForWork, Collection<CompetenceForm> competenceProfile) {
        this.personId = personId;
        this.availableForWork = availableForWork;
        this.competenceProfile = competenceProfile;
    }

    public Integer getPersonId() {
        return personId;
    }

    public AvailabilityForm getAvailableForWork() {
        return availableForWork;
    }

    public Collection<CompetenceForm> getCompetenceProfile() {
        return competenceProfile;
    }
}
