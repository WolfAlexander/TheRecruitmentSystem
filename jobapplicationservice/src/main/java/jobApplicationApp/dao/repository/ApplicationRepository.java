package jobApplicationApp.dao.repository;

import jobApplicationApp.entity.ApplicationEntity;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;


@Transactional
public interface ApplicationRepository extends CrudRepository<ApplicationEntity, Integer> {

    @Query(value = "SELECT * FROM application LIMIT :startId, :numberOfApplication", nativeQuery = true)
    Collection<ApplicationEntity> getXApplicationsFrom(@Param("startId") int startId,@Param("numberOfApplication") int numberOfApplications);

    @Query(value = "SELECT application.* FROM application, person WHERE application.person_id=person.id And firstname = :personName", nativeQuery = true)
    Collection<ApplicationEntity> getApplicationsByPersonName(@Param("personName") String personName);

    @Query(value = "SELECT application.*  from application, availability WHERE application.availability_id=availability.id AND from_date <= :fromDate AND to_date > :fromDate ", nativeQuery = true)
    Collection<ApplicationEntity> getApplicationsThatCanWorkFrom(@Param("fromDate")Date fromDate);

    @Query(value = "SELECT application.*  from application, availability WHERE application.availability_id=availability.id AND to_date >= :toDate AND from_date < :toDate", nativeQuery = true)
    Collection<ApplicationEntity> getApplicationsThatCanWorkTo(@Param("toDate")Date toDate);


  //  Collection<ApplicationEntity> findApplicationByIdAndLanguageId(Integer id, String Language)
}
