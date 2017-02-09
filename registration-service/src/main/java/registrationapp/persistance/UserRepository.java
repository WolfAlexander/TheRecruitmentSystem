package registrationapp.persistance;

import org.springframework.data.repository.CrudRepository;
import registrationapp.entity.User;

import java.util.List;

/**
 * This interface is a repository that extends the CrudRepository interface. Default CRUD
 * operations can be used to access the database. This interface handles the User entity.
 *
 * @author Albin Friedner
 */
public interface UserRepository extends CrudRepository<User, Long>{

    List<User> findByUsername(String username);
}
