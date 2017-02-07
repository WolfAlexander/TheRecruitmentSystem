package jobApplicationApp.dao.repository;

import jobApplicationApp.entity.ApplicationEntity;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Collection;


@Transactional
public interface ApplicationRepository extends CrudRepository<ApplicationEntity, Integer> {

    @Query(value = "SELECT * FROM application LIMIT :startId, :numberOfApplication", nativeQuery = true)
    Collection<ApplicationEntity> getXApplicationsFrom(@Param("startId") int startId,@Param("numberOfApplication") int numberOfApplications);
}
