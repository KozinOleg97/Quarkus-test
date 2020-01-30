package org.acme.quickstart.Beans.Roles;

import org.acme.quickstart.Entity.Role;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ResponseRoles {
    private List<Role> roleList;

    public ResponseRoles() {
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
