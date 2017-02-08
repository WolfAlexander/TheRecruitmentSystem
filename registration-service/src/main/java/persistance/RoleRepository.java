package persistance;

import org.springframework.data.repository.CrudRepository;
import entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
