package registrationapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Table(name = "credential")
public class CredentialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private Integer personId;

    @NotNull
    private String username;

    @NotNull
    private String password;

    public CredentialEntity(Integer personId, String username, String password) {
        this.personId = personId;
        this.username = username;
        this.password = password;
    }
}
