package registrationapp.dao.persistance.localized;

import org.springframework.data.repository.CrudRepository;
import registrationapp.entity.localized.LocalizedRoleEntity;

import javax.transaction.Transactional;

@Transactional
public interface LocalizedRoleRepository extends CrudRepository<LocalizedRoleEntity, Integer> {

    LocalizedRoleEntity getByLanguageIdAndRoleId(int languageId, int roleId);
}