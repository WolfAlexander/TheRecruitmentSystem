package jobApplicationApp.dao.repository.localized;

import jobApplicationApp.entity.localized.LocalizedStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface LocalizedStatusRepository extends CrudRepository<LocalizedStatus, Integer> {
    LocalizedStatus getByLanguageIdAndStatusId(int languageId, int statusId);
    LocalizedStatus getByLanguageIdAndTranslation(int languageId, String translation);
}