package jobApplicationApp.dao.repository.localized;

import jobApplicationApp.entity.localized.LocalizedRole;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

@Transactional
public interface LocalizedRoleRepository extends CrudRepository<LocalizedRole, Integer> {

    LocalizedRole getByLanguageIdAndRoleId(int languageId, int roleId);
}