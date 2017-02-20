package jobApplicationApp.dao.repository.localized;

import jobApplicationApp.entity.CompetenceEntity;
import jobApplicationApp.entity.localized.LocalizedCompetence;
import jobApplicationApp.entity.localized.LocalizedRole;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface LocalizedCompetenceRepository extends CrudRepository<LocalizedCompetence, Integer> {
    LocalizedCompetence getByLanguageIdAndCompetenceId(int languageId, int competenceId);
    LocalizedCompetence getByLanguageIdAndTranslation(int languageId, String translation);
}