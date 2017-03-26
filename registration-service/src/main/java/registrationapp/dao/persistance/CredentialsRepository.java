package registrationapp.dao.persistance;

import org.springframework.data.repository.CrudRepository;
import registrationapp.entity.CredentialEntity;


/**
 * This interface is a repository that extends the CrudRepository interface. Default CRUD
 * operations can be used to access the database. This interface handles the CredentialEntity.
 *
 */

public interface CredentialsRepository extends CrudRepository<CredentialEntity, Integer>
{
    CredentialEntity findByUsername(String username);
}
