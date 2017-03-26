package jobApplicationApp.dto.form;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Form for the user's role on the system
 *
 * @author Adrian Gortzak gortzak@kth.se
 */
@AllArgsConstructor
@Getter
public class RoleForm {


    /**
     * Name of the role
     * @return role name
     */
    private String name;
}
