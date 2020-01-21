package org.acme.quickstart.Core;

import org.acme.quickstart.Entity.Account;
import org.acme.quickstart.Beans.Login.ResponseLogin;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@ApplicationScoped
public class LoginHandler {

    @Inject
    ResponseLogin responseLogin;


     public LoginHandler() {
    }


    public byte[] doHash(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {


        byte[] bytesOfMessage = str.getBytes("UTF-8");

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] theDigest = md.digest(bytesOfMessage);

        //MessageDigest md5 = MessageDigest.getInstance(str);
        return theDigest;

    }


    public boolean checkToken(String login, byte[] token){

        Account res = Account.find("login = ?1 and password_hash = ?2",
                login, token).firstResult();

        if (res != null){
            return true;
        }else return false;
    }
}
