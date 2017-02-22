package authapp.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * This object represents a request that should be send to authentication service by client
 */
@Getter
@Setter
@AllArgsConstructor
public class AuthRequest implements Serializable {
    private static final long serialVersionUID = 7622816671628629971L;

    @NotNull
    private String username;
    @NotNull
    private String password;
}
