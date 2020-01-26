package org.acme.quickstart.WSResources;

import org.acme.quickstart.Beans.AdminPanel.RequestAccList;
import org.acme.quickstart.Beans.AdminPanel.RequestAddAdminRole;
import org.acme.quickstart.Core.LoginHandler;
import org.acme.quickstart.Core.RegistrationHandler;
import org.acme.quickstart.Entity.Account;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/admin")
public class AdminPanelRes {

    @Inject
    LoginHandler loginHandler;
    @Inject
    RegistrationHandler registrationHandler;

    @Path("/panel")
    //@RolesAllowed("client")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response showAccountList(RequestAccList request)  {

        byte[] hash = loginHandler.doHash(request.getPassword());


        String login = request.getLogin();

        if (!loginHandler.checkToken(login, hash, "admin")) {
            return Response.ok(false).build();
        }


        return Response.ok(Account.listAll()).build();

    }

    @Path("/addadmin")
    //@RolesAllowed("client")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response setAdminRole(RequestAddAdminRole request)  {

        byte[] hash = loginHandler.doHash(request.getPassword());


        String login = request.getLogin();

        if (!loginHandler.checkToken(login, hash, "admin")) {
            return Response.ok(null).build();
        }

        String accToChangeLogin = request.getAcc_to_change_login();

        Account account = Account.find("login", accToChangeLogin).firstResult();


        account.role = registrationHandler.selectRole("admin");
        account.persist();


        return Response.ok(account).build();

    }

    @Path("/removeaddmin")
    //@RolesAllowed("client")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response removeAdminRole(RequestAddAdminRole request)  {

        byte[] hash = loginHandler.doHash(request.getPassword());


        String login = request.getLogin();

        if (!loginHandler.checkToken(login, hash, "admin")) {
            return Response.ok(null).build();
        }

        String accToChangeLogin = request.getAcc_to_change_login();

        Account account = Account.find("login", accToChangeLogin).firstResult();


        account.role = registrationHandler.selectRole("client");
        account.persist();


        return Response.ok(account).build();

    }
}
