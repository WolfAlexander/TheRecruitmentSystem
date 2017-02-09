package jobApplicationApp.dao.repository;

import jobApplicationApp.entity.CompetenceProfileEntity;
import jobApplicationApp.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;


@Transactional
public interface CompetenceProfileRepository extends CrudRepository<CompetenceProfileEntity, Integer> {
}
