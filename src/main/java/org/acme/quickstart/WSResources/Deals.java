package org.acme.quickstart.WSResources;

import io.quarkus.security.identity.SecurityIdentity;
import org.acme.quickstart.Core.LoginHandler;
import org.acme.quickstart.Entity.Account;
import org.acme.quickstart.Entity.Deal;
import org.acme.quickstart.Beans.Deals.RequestDealList;
import org.acme.quickstart.Beans.Deals.ResponseDealList;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/deal")
@ApplicationScoped
public class Deals {

    @Inject
    LoginHandler loginHandler;
    @Inject
    ResponseDealList dealListRes;
    @Inject
    SecurityIdentity securityIdentity;

    @Path("/create")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewDeal(RequestDealList requestDealList) {

        return null;
    }



    @Path("/show")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAll(RequestDealList requestDealList) {


        String login = requestDealList.getLogin();

        if (!loginHandler.checkToken(login, requestDealList.getToken())) {
            return Response.ok(null).build();
        }

        Account account = Account.find("login", login).firstResult();

        List<Deal> dealList = Deal.list("status = ?1 and account_id = ?2", true, account.id);

        dealListRes.setDealList(dealList);
        return Response.ok(dealListRes).build();


    }


}
