package registrationapp.dao.persistance.localized;

import org.springframework.data.repository.CrudRepository;
import registrationapp.entity.localized.LanguageEntity;
import javax.transaction.Transactional;

/**
 * This interface is a repository that extends the CrudRepository interface. Default CRUD
 * operations can be used to access the database. This interface handles the LanguageEntity.
 *
 */

@Transactional
public interface LanguageRepository extends CrudRepository<LanguageEntity, Integer>
{
    LanguageEntity findByName(String name);
}