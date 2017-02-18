package registrationapp.dao.persistance;

import org.springframework.data.repository.CrudRepository;
import registrationapp.entity.PersonEntity;

import java.util.Collection;

/**
 * This interface is a repository that extends the CrudRepository interface. Default CRUD
 * operations can be used to access the database. This interface handles the User entity.
 *
 * @author Albin Friedner
 */
public interface PersonRepository extends CrudRepository<PersonEntity, Integer>{
    Collection <PersonEntity> findByFirstName(String firstName);

}
