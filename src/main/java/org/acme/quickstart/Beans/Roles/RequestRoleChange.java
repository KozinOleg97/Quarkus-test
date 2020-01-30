package org.acme.quickstart.Beans.Roles;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RequestRoleChange {

    private String login;
    private String newRole;

    public RequestRoleChange() {
    }

    public String getNewRole() {
        return newRole;
    }

    public void setNewRole(String newRole) {
        this.newRole = newRole;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
