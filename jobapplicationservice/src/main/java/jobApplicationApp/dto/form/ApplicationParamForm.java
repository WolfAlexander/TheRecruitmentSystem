package jobApplicationApp.dto.form;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;

@Getter
@AllArgsConstructor
public class ApplicationParamForm {

    private String name;
    private AvailabilityForm availableForWork;
    private Collection<CompetenceForm> competenceProfile;
}
