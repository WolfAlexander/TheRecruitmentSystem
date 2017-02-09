package entity;

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
public class Role
{
    @Id
    private Long roleId;
    private String roleName;
    @OneToMany(mappedBy = "role")
    private List<User> users;

    protected Role(){}

    public Role(Long roleId, String roleName)
    {
        this.roleId = roleId;
        this.roleName = roleName;
        users = new ArrayList<>();
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void addUser(User user)
    {
        if (!getUsers().contains(user))
        {
            getUsers().add(user);
            if (user.getRole() != null)
            {
                user.getRole().getUsers().remove(user);
            }
            user.setRole(this);
        }
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return String.format("Role id = %s name = %s", roleId, roleName);
    }
}