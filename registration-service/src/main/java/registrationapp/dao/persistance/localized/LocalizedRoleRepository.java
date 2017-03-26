package registrationapp.dao.persistance.localized;

import org.springframework.data.repository.CrudRepository;
import registrationapp.entity.localized.LocalizedRoleEntity;

import javax.transaction.Transactional;

/**
 * This interface is a repository that extends the CrudRepository interface. Default CRUD
 * operations can be used to access the database. This interface handles the LocalizedRoleEntity.
 *
 */

@Transactional
public interface LocalizedRoleRepository extends CrudRepository<LocalizedRoleEntity, Integer> {

    LocalizedRoleEntity getByLanguageIdAndRoleId(int languageId, int roleId);
}