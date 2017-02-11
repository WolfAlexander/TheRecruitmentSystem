package jobApplicationApp.dto.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Component
@Getter
@NoArgsConstructor
public class ApplicationForm {

  @NotNull
  @Min(0)
  int personId;

  @NotNull
  AvailabilityForm availableForWork;

  @NotNull
  Collection<CompetenceForm> competenceProfile;

    public ApplicationForm(Integer personId, AvailabilityForm availableForWork, Collection<CompetenceForm> competenceProfile) {
        this.personId = personId;
        this.availableForWork = availableForWork;
        this.competenceProfile = competenceProfile;
    }
}
