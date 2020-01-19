package org.acme.quickstart;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.quickstart.Entity.Account;
import org.acme.quickstart.Entity.Role;
import org.acme.quickstart.Entity.TAccount;
import org.acme.quickstart.Entity.TRole;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.acme.quickstart.Core.RegistrationHandler;

@Path("/test")
public class Test {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PanacheEntityBase> getTest() {

        return TAccount.listAll();

    }


    @Transactional
    @GET
    @Path("/acc")
    @Produces(MediaType.APPLICATION_JSON)
    public Account test2() {


        //Role role = new Role();
        //role.role = "base";
        //role.persist();

        Account account = new Account();
        account.login = "admin";
        account.password_hash = "1";
        //account.role = Account.Role.admin;
        account.persist();

        return account;
    }

    @Transactional
    @GET
    @Path("/qwe")
    @Produces(MediaType.APPLICATION_JSON)
    public TAccount test3() throws NoSuchAlgorithmException, UnsupportedEncodingException {

       /* TRole tRole = new TRole();
        tRole.name = "admin";
        tRole.persist();*/

        List<PanacheEntityBase> roles = TRole.listAll();


        RegistrationHandler handler = new RegistrationHandler();


        TAccount TAccount = new TAccount();
        TAccount.login = "admin";
        TAccount.password_hash = handler.doHash( "admin");
        TAccount.role = (TRole) roles.get(0);

        TAccount.persist();

        return TAccount;
    }

}
