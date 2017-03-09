package jobApplicationApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * This object represents a request that should be send to authentication service by client
 *
 * @author WolfAlexander nikal@kth.se
 */
@Getter
@AllArgsConstructor
public class AuthRequest implements Serializable {
    private static final long serialVersionUID = 7622816671628629971L;

    @NotNull
    @Size(min = 2)
    private String username;

    @NotNull
    @Size(min = 8)
    private String password;
}
