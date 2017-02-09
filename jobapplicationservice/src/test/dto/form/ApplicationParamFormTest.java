package dto.form;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;

@Getter
@AllArgsConstructor
public class ApplicationParamFormTest {

    private String name;
    private AvailabilityFormTest availableForWork;
    private Collection<CompetenceFormTest> competenceProfile;
}
