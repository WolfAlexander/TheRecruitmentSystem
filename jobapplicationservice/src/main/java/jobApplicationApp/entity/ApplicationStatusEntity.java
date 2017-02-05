package jobApplicationApp.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "status")
public class ApplicationStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String name;


    /**
     * Get the name of the status
     * @return name of status
     */
    public String getName(){
        return name;
    }
}
