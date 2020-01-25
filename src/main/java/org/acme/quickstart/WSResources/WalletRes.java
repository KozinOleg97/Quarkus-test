package org.acme.quickstart.WSResources;


import org.acme.quickstart.Beans.Deals.RequestDealList;
import org.acme.quickstart.Beans.Wallet.RequestWalletShow;
import org.acme.quickstart.Core.LoginHandler;
import org.acme.quickstart.Entity.Account;
import org.acme.quickstart.Entity.Deal;
import org.acme.quickstart.Entity.Wallet;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Path("/wallet")
public class WalletRes {

    @Inject
    LoginHandler loginHandler;



    @Path("/show")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAll(RequestWalletShow request) throws NoSuchAlgorithmException {

        byte[] hash = loginHandler.doHash(request.getPassword());

        String login = request.getLogin();

        if (!loginHandler.checkToken(login, hash)) {
            return Response.ok(null).build();
        }

        Account account = Account.find("login", login).firstResult();

        Wallet res = account.wallet;

        return Response.ok(res).build();


    }

}
