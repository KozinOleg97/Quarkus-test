package org.acme.quickstart.Core;

import org.acme.quickstart.Entity.Account;
import org.acme.quickstart.Beans.Login.ResponseLogin;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@ApplicationScoped
public class LoginHandler {

    @Inject
    ResponseLogin responseLogin;


     public LoginHandler() {
    }


    public byte[] doHash(String str) throws NoSuchAlgorithmException {


        byte[] bytesOfMessage = str.getBytes(StandardCharsets.UTF_8);

        MessageDigest md = MessageDigest.getInstance("MD5");

        //MessageDigest md5 = MessageDigest.getInstance(str);
        return md.digest(bytesOfMessage);

    }


    public boolean checkToken(String login, byte[] token){

        Account res = Account.find("login = ?1 and password_hash = ?2",
                login, token).firstResult();

        return res != null;
    }

    public boolean checkAccountExist(String login , byte[] hash) {
        Account res = Account.find("login = ?1 and password_hash = ?2",
                login, hash).firstResult();

        return res != null;
    }
}
