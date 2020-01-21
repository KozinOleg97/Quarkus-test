package org.acme.quickstart.Core;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.acme.quickstart.Entity.Account;
import org.acme.quickstart.Entity.PersonMainData;
import org.acme.quickstart.Entity.Role;
import org.acme.quickstart.Entity.Wallet;
import org.acme.quickstart.POJO.RequestClient;
import org.acme.quickstart.POJO.ResponseClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RegistrationHandler {


    @Inject
    ResponseClient responseClient;



    final String BASE_ROLE = "client";


    public ResponseClient doRegister(RequestClient requestClient) throws NoSuchAlgorithmException, UnsupportedEncodingException {


        if (checkLogin(requestClient.getLogin())) {

            addNewAccount(requestClient);

            responseClient.setNameClient(requestClient.getLogin());
            responseClient.setToken(doHash(requestClient.getPassword()));
            return responseClient;
        } else {
            responseClient.setNameClient(requestClient.getLogin());
            responseClient.setToken(null);
            return responseClient;
        }

    }

    //TODO add exception

    private void addNewAccount(RequestClient data) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        //List<PanacheEntityBase> roleList = Role.listAll();


        //Role panacheEntityBase = (Role) roleList.get(0);

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
        ;//  (Role) roleList.get(0);

        //account.simple_role =
        account.wallet = wallet;
        account.main_data = personMainData;


        //account.wallet =
        // account.persist();
    }

    public Role selectRole(String inputRole) {

        List<Role> roleList = Role.listAll();

        Role resRole = null;

        for (Role curRole : roleList) {
            if (curRole.name == inputRole) {
                resRole = curRole;
            }
        }

        if (resRole == null) {
            resRole = roleList.get(1);
        }

        return resRole;

    }

    private boolean checkLogin(String login) {
        long count = Account.count("login", login);
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
