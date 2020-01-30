package org.acme.quickstart.Beans.Registration;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RequestClient {

    /*{
    "login": "NewUser4",
    "password": "NewUser3Pass",
    "name": "Qwe",
    "surname": "Asd",
    "role": ""
}*/


    private String login;
    private String password;



    public RequestClient() {
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

}
