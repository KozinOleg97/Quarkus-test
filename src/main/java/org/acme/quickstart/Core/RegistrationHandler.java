package org.acme.quickstart.Core;

import org.acme.quickstart.Entity.Account;
import org.acme.quickstart.Entity.PersonMainData;
import org.acme.quickstart.Entity.Role;
import org.acme.quickstart.Entity.Wallet;
import org.acme.quickstart.Beans.Registration.RequestClient;
import org.acme.quickstart.Beans.Registration.ResponseClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@ApplicationScoped
public class RegistrationHandler {


    @Inject
    ResponseClient responseClient;


    final String BASE_ROLE = "client";

     RegistrationHandler() {
    }


    //TODO add exception

    public void addNewAccount(RequestClient data) throws NoSuchAlgorithmException {


        Wallet wallet = new Wallet();
        wallet.is_active = false;
        wallet.deposit = 0;
        wallet.outlay = 0;
        wallet.persist();

        PersonMainData personMainData = new PersonMainData();
        personMainData.Name = data.getName();
        personMainData.Surname = data.getSurname();
        personMainData.persist();

        byte[] hashPass = doHash(data.getPassword());
        Account account = new Account();
        account.login = data.getLogin();
        account.password_hash = doHash(data.getPassword());
        account.role = selectRole(data.getRole());

        account.wallet = wallet;
        account.main_data = personMainData;


        account.persist();
    }

    private Role selectRole(String inputRole) {

        List<Role> roleList = Role.listAll();

        Role resRole = null;

        for (Role curRole : roleList) {
            if (curRole.name.equals(inputRole)) {
                resRole = curRole;
            }
        }

        if (resRole == null) {
            resRole = roleList.get(1);
        }

        return resRole;

    }

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
