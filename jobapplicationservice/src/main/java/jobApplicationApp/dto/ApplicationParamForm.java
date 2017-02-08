package jobApplicationApp.dto;


import jobApplicationApp.entity.CompetenceEntity;

import java.util.Collection;
import java.util.Date;

public class ApplicationParamForm {

    private AvailabilityForm availability;

    private Collection<CompetenceForm> competences;
    private String name;

    public ApplicationParamForm(AvailabilityForm availability, Collection<CompetenceForm> competences, String name) {
        this.availability = availability;
        this.competences = competences;
        this.name = name;
    }

    public AvailabilityForm getAvailability() {
        return availability;
    }

    public Collection<CompetenceForm> getCompetences() {
        return competences;
    }

    public String getName() {
        return name;
    }
}
