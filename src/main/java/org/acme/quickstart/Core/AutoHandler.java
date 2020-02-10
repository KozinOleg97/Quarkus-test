package org.acme.quickstart.Core;

import org.acme.quickstart.Entity.Account;
import org.acme.quickstart.Entity.Auto;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AutoHandler {


    public AutoHandler() {
    }

    public boolean checkAutoExist(String regNumber) {

        long res = Auto.count("reg_number", regNumber);

        return res != 0;
    }

    public void addAuto(String regNumber, String login) {

        Account account = Account.find("login", login).firstResult();

        Auto auto = new Auto();
        auto.reg_number = regNumber;
        auto.account = account;

        auto.persist();
    }
}
