package org.acme.quickstart.WSResources;

import org.acme.quickstart.Beans.Deals.RequestDealCreate;
import org.acme.quickstart.Core.LoginHandler;
import org.acme.quickstart.Entity.Account;
import org.acme.quickstart.Entity.Box;
import org.acme.quickstart.Entity.Deal;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/deal")
@ApplicationScoped
public class DealsRes {

    @Inject
    LoginHandler handler;
//    @Inject
//    ResponseDealList dealListRes;

    @Path("/create")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createNewDeal(RequestDealCreate request) {

        byte[] hash = handler.doHash(request.getPassword());

        Account account = Account.find("login = ?1 and password_hash = ?2",
                request.getLogin(), hash).firstResult();


        Box box = Box.findById(Long.valueOf(request.getBox_id()));


        if (account == null) {
            return null;
        }

        box.occupied = false;
        box.persist();

        Deal deal = new Deal();
        deal.account = account;
        deal.status = true;
        deal.box = box;
        deal.persist();


        return Response.ok(deal).build();
    }

    @Path("/show")
    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@Context SecurityContext securityContext) {
        Account account = Account.find("login", securityContext.getUserPrincipal().getName()).firstResult();

        List<Deal> dealList = Deal.list("status = ?1 and account_id = ?2", true, account.id);

        //dealListRes.setDealList(dealList);
        return Response.ok(dealList).build();
    }


}
