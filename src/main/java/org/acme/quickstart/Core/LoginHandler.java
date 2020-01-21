package org.acme.quickstart.Core;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.acme.quickstart.Entity.Account;
import org.acme.quickstart.Entity.Role;
import org.acme.quickstart.POJO.ResponseLogin;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@ApplicationScoped
public class LoginHandler {

    //@Inject
    //ResponseLogin responseLogin;

    public ResponseLogin doLogin(String login, String pass) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] hash = doHash(pass);

        //PanacheEntityBase panacheEntityBase = Account.findAll().firstResult();


        //List<PanacheEntityBase> panacheEntityBases = Account.listAll();

        //PanacheEntityBase panacheEntityBase = Account.find("login = ?1", login).firstResult();


        Account res = Account.find("login = ?1 and password_hash = ?2",
                login, hash).firstResult();

        ResponseLogin responseLogin = new ResponseLogin();

        if (res != null) {
            responseLogin.setResult(true);
            responseLogin.setToken(hash);
            responseLogin.setRole(res.role.name);
            return responseLogin;
        } else {
            responseLogin.setResult(false);
            //responseLogin.setToken(hash);
            return responseLogin;
        }
    }

    public byte[] doHash(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {


        byte[] bytesOfMessage = str.getBytes("UTF-8");

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] theDigest = md.digest(bytesOfMessage);

        //MessageDigest md5 = MessageDigest.getInstance(str);
        return theDigest;

    }
}
