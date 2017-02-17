package registrationapp.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class roles that user in the recruit system can have. The entity is persisted
 * in an external database.
 *
 * @author Albin Friedner
 */

@Entity
@Getter
public class Role
{
    @Id
    @Column(name = "id")
    private Long roleId;
    @Column(name = "name")
    private String roleName;


    protected Role(){}

    public Role(Long roleId, String roleName)
    {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return String.format("Role id = %s name = %s", roleId, roleName);
    }
}