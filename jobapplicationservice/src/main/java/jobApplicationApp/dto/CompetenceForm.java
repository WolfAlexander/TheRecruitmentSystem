package jobApplicationApp.dto;


import javax.validation.constraints.NotNull;

public class CompetenceForm {

    @NotNull
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public CompetenceForm(String name) {

        this.name = name;
    }
}
