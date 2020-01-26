package org.acme.quickstart.Beans.Wallet;

public class RequstWalletAddMoney {

    private String login;
    private String password;
    private int money_to_add;

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

    public int getMoney_to_add() {
        return money_to_add;
    }

    public void setMoney_to_add(int money_to_add) {
        this.money_to_add = money_to_add;
    }
}
