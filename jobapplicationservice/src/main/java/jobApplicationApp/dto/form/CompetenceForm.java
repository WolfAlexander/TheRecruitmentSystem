package jobApplicationApp.dto.form;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
@Component
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompetenceForm {

    @NotNull
    private String name;

    @NotNull
    @Min(0)
    @Max(120)
    private int yearsOfExperience;

    @Override
    public String toString() {
        return "Competence{" + "name='" + name + '\'' + ", yearsOfExperience=" + yearsOfExperience + '}';
    }
}
