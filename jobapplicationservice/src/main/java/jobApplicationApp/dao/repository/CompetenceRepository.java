package jobApplicationApp.dao.repository;

import jobApplicationApp.entity.CompetenceEntity;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

@Transactional
public interface CompetenceRepository extends CrudRepository<CompetenceEntity, Integer> {
    CompetenceEntity findByName(String name);
}
