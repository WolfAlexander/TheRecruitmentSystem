package jobApplicationApp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "role")
@NoArgsConstructor
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String name;

    public RoleEntity(String name) {
        this.name = name;
    }

    /**
     * Get role name
     * @return name of role
     */
    public String getName(){
        return name;
    }

}
