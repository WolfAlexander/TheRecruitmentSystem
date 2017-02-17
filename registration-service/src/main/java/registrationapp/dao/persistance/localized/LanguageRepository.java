package registrationapp.dao.persistance.localized;


import org.springframework.data.repository.CrudRepository;
import registrationapp.entity.localized.LanguageEntity;

import javax.transaction.Transactional;

@Transactional
public interface LanguageRepository extends CrudRepository<LanguageEntity, Integer> {
    LanguageEntity findByName(String name);
}