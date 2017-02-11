package jobApplicationApp.dao.repository;

import jobApplicationApp.entity.ApplicationEntity;
import jobApplicationApp.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;


@Transactional
public interface PersonRepository extends CrudRepository<PersonEntity, Integer> {
}
