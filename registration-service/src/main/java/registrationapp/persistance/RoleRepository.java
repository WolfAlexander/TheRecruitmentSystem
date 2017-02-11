package registrationapp.persistance;

import org.springframework.data.repository.CrudRepository;
import registrationapp.entity.Role;


/**
 * This interface is a repository that extends the CrudRepository interface. Default CRUD
 * operations can be used to access the database. This interface handles the User entity.
 *
 * @author Albin Friedner
 */

public interface RoleRepository extends CrudRepository<Role, Long> {
}