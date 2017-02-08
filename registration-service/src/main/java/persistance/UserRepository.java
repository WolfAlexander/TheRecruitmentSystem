package persistance;

import org.springframework.data.repository.CrudRepository;
import entity.User;
import java.util.List;

/**
 * Created by friedner on 2017-02-08.
 */
public interface UserRepository extends CrudRepository<User, Long>{

    List<User> findByUsername(String username);
}
