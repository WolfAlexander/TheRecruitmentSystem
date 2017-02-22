package jobApplicationApp.dao.repository;

import jobApplicationApp.entity.AvailabilityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;



@Transactional
public interface AvailableRepository extends CrudRepository<AvailabilityEntity, Integer> {

    AvailabilityEntity findByFromDateAndToDate(Date fromDate, Date toDate);
}
