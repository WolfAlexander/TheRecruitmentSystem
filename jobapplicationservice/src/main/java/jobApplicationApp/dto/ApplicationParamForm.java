package jobApplicationApp.dto;


import jobApplicationApp.entity.CompetenceEntity;

import java.util.Collection;
import java.util.Date;

public class ApplicationParamForm {

    private Date availableFrom;
    private Date availableTo;

    private Collection<CompetenceEntity> competences;
    private String name;


    public ApplicationParamForm(Date availableFrom, Date availableTo, String name) {
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
        this.name = name;
    }

    public Date getAvailableFrom() {
        return availableFrom;
    }

    public Date getAvailableTo() {
        return availableTo;
    }

    public String getName() {
        return name;
    }

    public Collection<CompetenceEntity> getCompetences() {
        return competences;
    }
}
