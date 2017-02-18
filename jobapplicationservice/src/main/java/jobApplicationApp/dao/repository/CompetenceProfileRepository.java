package jobApplicationApp.dao.repository;

import jobApplicationApp.entity.CompetenceProfileEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;


@Transactional
public interface CompetenceProfileRepository extends CrudRepository<CompetenceProfileEntity, Integer> {
}
