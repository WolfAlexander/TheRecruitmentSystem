package jobApplicationApp.dao.repository;

import jobApplicationApp.entity.CompetenceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CompetenceRepository extends CrudRepository<CompetenceEntity, Integer> {
    CompetenceEntity findByName(String name);
}
