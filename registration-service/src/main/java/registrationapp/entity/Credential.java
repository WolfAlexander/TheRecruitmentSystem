package registrationapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Table(name = "credential")
public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private Long personId;

    @NotNull
    private String username;

    @NotNull
    private String password;

    public Credential(Long personId, String username, String password) {
        this.personId = personId;
        this.username = username;
        this.password = password;
    }
}
