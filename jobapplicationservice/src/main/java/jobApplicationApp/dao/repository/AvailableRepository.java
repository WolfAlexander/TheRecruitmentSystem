package jobApplicationApp.dao.repository;

import jobApplicationApp.entity.AvailabilityEntity;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Transactional
public interface AvailableRepository extends CrudRepository<AvailabilityEntity, Integer> {

    AvailabilityEntity findByFromDateAndToDate(Date fromDate, Date toDate);
}
