package jobApplicationApp.dao.repository.localized;

import jobApplicationApp.entity.localized.LanguageEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface LanguageRepository extends CrudRepository<LanguageEntity, Integer> {
    LanguageEntity findByName(String name);
}