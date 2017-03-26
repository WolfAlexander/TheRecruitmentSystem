package jobApplicationApp.dao.repository;

import jobApplicationApp.entity.ApplicationStatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface ApplicationStatusRepository extends CrudRepository<ApplicationStatusEntity, Integer> {

    ApplicationStatusEntity findByName(String name);
}
