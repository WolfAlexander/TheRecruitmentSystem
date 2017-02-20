package jobApplicationApp.dto.response;

import jobApplicationApp.dto.form.PersonForm;
import jobApplicationApp.entity.ApplicationEntity;
import jobApplicationApp.entity.ApplicationStatusEntity;
import jobApplicationApp.entity.CompetenceProfileEntity;
import lombok.Getter;

import java.util.Collection;
import java.util.Date;

@Getter
public class ApplicationResponse {

    private int id;
    private PersonForm person;
    private ApplicationStatusEntity status;
    private Collection<CompetenceProfileEntity> competenceProfile;
    private Date dateOfRegistration;

    public ApplicationResponse(ApplicationEntity application, PersonForm person){
      this.id = application.getId();
      this.status = application.getStatus();
      this.competenceProfile =application.getCompetenceProfile();
      this.dateOfRegistration = application.getDateOfRegistration();
      this.person = person;
    }
}
