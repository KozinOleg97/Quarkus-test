package org.acme.quickstart.Beans.AdminPanel;

public class RequestAddAdminRole {

    private String login;
    private String password;
    private String acc_to_change_login;

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

    public String getAcc_to_change_login() {
        return acc_to_change_login;
    }

    public void setAcc_to_change_login(String acc_to_change_login) {
        this.acc_to_change_login = acc_to_change_login;
    }
}
