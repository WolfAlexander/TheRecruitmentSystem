package jobApplicationApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Status entity
 *
 * @author Adrian Gortzak gortzak@kth.se
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "status")
@Getter
public class ApplicationStatusEntity {


    /**
     *The id of the status
     * @return id of status
     */
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    /**
     * The name of the status
     * @return the status name
     */
    @NotNull
    private String name;


    public ApplicationStatusEntity(String name) {
        this.name = name;
    }

    /**
     * Set the name of the status
     * @param name to be changed to
     */
    public void setName(String name) {
        this.name = name;
    }

}
