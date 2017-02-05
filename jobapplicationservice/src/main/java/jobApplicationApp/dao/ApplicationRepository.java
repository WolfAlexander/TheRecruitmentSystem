package jobApplicationApp.dao;

import jobApplicationApp.entity.ApplicationEntity;

import jobApplicationApp.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;


@Transactional
interface ApplicationRepository extends CrudRepository<ApplicationEntity, Integer> {


}
