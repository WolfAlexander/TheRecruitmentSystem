package registrationapp.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import registrationapp.entity.CredentialEntity;
import registrationapp.entity.PersonEntity;


@Getter
@AllArgsConstructor
public class UserCredentialsDTO
{
    private PersonEntity personEntity;
    private CredentialEntity credentialEntity;
}
