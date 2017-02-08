package jobApplicationApp.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CompetenceForm {

    @NotNull
    private String name;

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    @NotNull
    @Min(0)
    @Max(120)
    private int yearsOfExperience;


    public CompetenceForm() {
    }

    public CompetenceForm(String name, int yearsOfExperience) {
        this.name = name;
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "Competence{" +
                "name='" + name + '\'' +
                ", yearsOfExperience=" + yearsOfExperience +
                '}';
    }
}
