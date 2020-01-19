package org.acme.quickstart.Core;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.quickstart.Entity.Account;
import org.acme.quickstart.Entity.Role;
import org.acme.quickstart.Entity.TAccount;
import org.acme.quickstart.Entity.TRole;
import org.acme.quickstart.POJO.ResponseClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@ApplicationScoped
public class RegistrationHandler {

    @Inject
    ResponseClient responseClient;
    TAccount account;
    TRole role;

    public ResponseClient doRegister(String login, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        if (checkLogin(login)) {
            byte[] hashPass = doHash(password);
            addNewAccount(login, hashPass);

            responseClient.setNameClient(login);
            responseClient.setToken(hashPass);
            return responseClient;
        }else {
            responseClient.setNameClient(login);
            responseClient.setToken(null);
            return responseClient;
        }

    }

    //TODO add exception
    private void addNewAccount(String login, byte[] hashPass) {

        List<PanacheEntityBase> roleList = TRole.listAll();



        TAccount account = new TAccount();
        account.login = login;
        account.password_hash = hashPass;
        account.role = (TRole) roleList.get(0);
        account.persist();
    }

    private boolean checkLogin(String login) {
        long count = TAccount.count("login", login);
        if (count == 0) {
            return true;
        } else return false;
    }

    public byte[] doHash(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        byte[] bytesOfMessage = str.getBytes("UTF-8");

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] theDigest = md.digest(bytesOfMessage);

        //MessageDigest md5 = MessageDigest.getInstance(str);
        return theDigest;
    }

}
