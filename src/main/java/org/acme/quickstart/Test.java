package org.acme.quickstart;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.quickstart.Entity.Account;
import org.acme.quickstart.Entity.Role;

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

        return Account.listAll();

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
        //account.password_hash = "1";
        //account.role = Account.Role.admin;
        account.persist();

        return account;
    }

    @Transactional
    @GET
    @Path("/qwe")
    @Produces(MediaType.APPLICATION_JSON)
    public Account test3() throws NoSuchAlgorithmException, UnsupportedEncodingException {

       /* Role tRole = new Role();
        tRole.name = "admin";
        tRole.persist();*/

        List<PanacheEntityBase> roles = Role.listAll();


        RegistrationHandler handler = new RegistrationHandler();


        Account Account = new Account();
        Account.login = "admin";
        Account.password_hash = handler.doHash( "admin");
        Account.role = (Role) roles.get(0);

        Account.persist();

        return Account;
    }

}
