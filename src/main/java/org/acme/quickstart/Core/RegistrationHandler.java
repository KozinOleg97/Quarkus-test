package org.acme.quickstart.Core;

import org.acme.quickstart.Entity.Account;
import org.acme.quickstart.POJO.ResponseClient;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegistrationHandler {

    @Inject
    ResponseClient responseClient;

    public ResponseClient doRegistrate(String login, String password) throws NoSuchAlgorithmException {

        if (checkLogin(login)) {
            MessageDigest hashPass = doHash(password);
            addNewAccount(login, hashPass);

            responseClient.setNameClient(login);
            responseClient.setToken(hashPass.toString());
            return responseClient;
        }
        return null;
    }

    //TODO add exception
    @Transactional
    private void addNewAccount(String login, MessageDigest hashPass) {

        Account account = new Account();
        account.login = login;
        account.password_hash = hashPass.toString();
        account.role = null;
        account.persist();
    }

    private boolean checkLogin(String login) {
        long count = Account.count("login", login);
        if (count == 0) {
            return true;
        } else return false;
    }

    private MessageDigest doHash(String str) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance(str);
        return md5;
    }

}
