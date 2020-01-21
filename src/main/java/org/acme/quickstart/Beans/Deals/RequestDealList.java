package org.acme.quickstart.Beans.Deals;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RequestDealList {

    private String login;
    private byte[] token;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public byte[] getToken() {
        return token;
    }

    public void setToken(byte[] token) {
        this.token = token;
    }
}
