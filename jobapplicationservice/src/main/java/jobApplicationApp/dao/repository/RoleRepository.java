package jobApplicationApp.dao.repository;

import jobApplicationApp.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {
    RoleEntity findByName(String name);
}
