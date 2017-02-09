package jobApplicationApp.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Table(name = "status")
public class ApplicationStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String name;

    public ApplicationStatusEntity(String name) {
        this.name = name;
    }

    /**
     * Get the name of the status
     * @return name of status
     */
    public String getName(){
        return name;
    }
}
