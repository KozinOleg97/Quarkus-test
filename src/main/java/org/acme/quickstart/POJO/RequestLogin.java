package org.acme.quickstart.POJO;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RequestLogin {


                /*{
    "login": "NewUser4",
    "password": "NewUser3Pass",
}*/

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
