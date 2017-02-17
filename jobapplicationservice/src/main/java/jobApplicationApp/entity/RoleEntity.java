package jobApplicationApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Collection;

@Entity
@Table(name = "role")
@Getter
@NoArgsConstructor
public class RoleEntity {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
     private Integer id;

    @NotNull
    private String name;

    public RoleEntity(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
