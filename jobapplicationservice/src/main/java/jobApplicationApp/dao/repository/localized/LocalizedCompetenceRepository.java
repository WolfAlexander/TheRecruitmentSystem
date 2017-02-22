package jobApplicationApp.dao.repository.localized;

import jobApplicationApp.entity.localized.LocalizedCompetence;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface LocalizedCompetenceRepository extends CrudRepository<LocalizedCompetence, Integer> {
    LocalizedCompetence getByLanguageIdAndCompetenceId(int languageId, int competenceId);
    LocalizedCompetence getByLanguageIdAndTranslation(int languageId, String translation);
}