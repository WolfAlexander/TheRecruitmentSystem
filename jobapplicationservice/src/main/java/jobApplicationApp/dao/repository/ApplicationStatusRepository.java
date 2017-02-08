package jobApplicationApp.dao.repository;

import jobApplicationApp.entity.ApplicationStatusEntity;
import org.springframework.data.repository.CrudRepository;


import javax.transaction.Transactional;


@Transactional
public interface ApplicationStatusRepository extends CrudRepository<ApplicationStatusEntity, Integer> {

    ApplicationStatusEntity findByName(String name);
}
