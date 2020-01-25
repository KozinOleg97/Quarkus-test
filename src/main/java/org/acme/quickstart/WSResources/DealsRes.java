package org.acme.quickstart.WSResources;

import org.acme.quickstart.Beans.Deals.RequestDealCreate;
import org.acme.quickstart.Core.LoginHandler;
import org.acme.quickstart.Entity.Account;
import org.acme.quickstart.Entity.Box;
import org.acme.quickstart.Entity.Deal;
import org.acme.quickstart.Beans.Deals.RequestDealList;
import org.acme.quickstart.Beans.Deals.ResponseDealList;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Path("/deal")
@ApplicationScoped
public class DealsRes {

    @Inject
    LoginHandler handler;
    @Inject
    ResponseDealList dealListRes;

    @Path("/create")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createNewDeal(RequestDealCreate request) throws NoSuchAlgorithmException {

        byte[] hash = handler.doHash(request.getPassword());

        Account account = Account.find("login = ?1 and password_hash = ?2",
                request.getLogin(), hash).firstResult();


        Box box = Box.findById(Long.valueOf(request.getBox_id()));


        if (account == null) {
            return null;
        }

        Deal deal = new Deal();
        deal.account = account;
        deal.status = true;
        deal.box = box;
        deal.persist();


        return Response.ok(deal).build();
    }

    @Path("/show")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAll(RequestDealList requestDealList) throws NoSuchAlgorithmException {

        byte[] hash = handler.doHash(requestDealList.getPassword());


        String login = requestDealList.getLogin();

        if (!handler.checkToken(login, hash)) {
            return Response.ok(null).build();
        }

        Account account = Account.find("login", login).firstResult();

        List<Deal> dealList = Deal.list("status = ?1 and account_id = ?2", true, account.id);

        dealListRes.setDealList(dealList);
        return Response.ok(dealListRes).build();


    }


}
