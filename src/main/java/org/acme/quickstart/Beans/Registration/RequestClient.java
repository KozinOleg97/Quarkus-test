package org.acme.quickstart.Beans.Registration;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RequestClient {

    /*{
    "login": "NewUser4",
    "password": "NewUser3Pass",
    "empl_doc": "",
    "name": "Qwe",
    "surname": "Asd",
    "role": ""
}*/


    private String login;
    private String password;
    private String empl_doc;
    private String name;
    private String surname;
    private String role;


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

    public String getEmpl_doc() {
        return empl_doc;
    }

    public void setEmpl_doc(String empl_doc) {
        this.empl_doc = empl_doc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
