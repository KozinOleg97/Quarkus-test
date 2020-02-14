package org.acme.quickstart.Core;

import org.acme.quickstart.Beans.Registration.RequestClient;
import org.acme.quickstart.Beans.Registration.ResponseClient;
import org.acme.quickstart.Entity.Account;
import org.acme.quickstart.Entity.PersonMainData;
import org.acme.quickstart.Entity.Role;
import org.acme.quickstart.Exceptions.NoSuchRole;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@ApplicationScoped
public class RegistrationHandler {


    final String BASE_ROLE = "client";

    RegistrationHandler() {
    }


    public void addNewAccount(RequestClient data) throws NoSuchRole {

        PersonMainData personMainData = new PersonMainData();
        personMainData.Name = "";
        personMainData.Surname = "";
        //personMainData.persist();


        Account account = new Account();
        account.login = data.getLogin();
        account.password = data.getPassword();
        account.role = selectRole(BASE_ROLE);
        account.main_data = personMainData;

        account.persist();
    }

    public Role selectRole(String inputRole) throws NoSuchRole {

        Role resRole = Role.find("name", inputRole).firstResult();


        if (resRole == null) {
            throw new NoSuchRole("Role select err", inputRole);
        }

        return resRole;

    }


    /**
     * dose login exist
     * @param login
     * @return
     */
    public boolean checkLogin(String login) {
        long count = Account.count("login", login);
        return count == 0;
    }

    public byte[] doHash(String str) throws NoSuchAlgorithmException {


        byte[] bytesOfMessage = str.getBytes(StandardCharsets.UTF_8);

        MessageDigest md = MessageDigest.getInstance("MD5");

        return md.digest(bytesOfMessage);

    }

}
