package registrationapp.dao.persistance;

import org.springframework.data.repository.CrudRepository;
import registrationapp.entity.RoleEntity;


/**
 * This interface is a repository that extends the CrudRepository interface. Default CRUD
 * operations can be used to access the database. This interface handles the RoleEntity.
 *
 */

public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {
}
