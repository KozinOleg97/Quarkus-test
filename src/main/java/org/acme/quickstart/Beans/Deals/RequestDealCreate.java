package org.acme.quickstart.Beans.Deals;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RequestDealCreate {

    private String login;
    private String password;
    private String box_id;


    public RequestDealCreate() {
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBox_id() {
        return box_id;
    }

    public void setBox_id(String box_id) {
        this.box_id = box_id;
    }
}
