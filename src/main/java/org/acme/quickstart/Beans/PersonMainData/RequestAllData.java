package org.acme.quickstart.Beans.PersonMainData;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Deprecated
public class RequestAllData {

    private String login;
    private String password;


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
}
