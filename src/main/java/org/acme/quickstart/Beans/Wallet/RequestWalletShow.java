package org.acme.quickstart.Beans.Wallet;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RequestWalletShow {


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
