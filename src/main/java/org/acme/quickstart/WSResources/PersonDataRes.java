package org.acme.quickstart.WSResources;

import org.acme.quickstart.Beans.Deals.RequestDealCreate;
import org.acme.quickstart.Beans.PersonMainData.*;
import org.acme.quickstart.Core.LoginHandler;
import org.acme.quickstart.Entity.*;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Path("/persondata")
public class PersonDataRes {

    @Inject
    ResponseAllData responseAllData;
    @Inject
    LoginHandler handler;

    @Path("/show")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAll(RequestAllData request) {

        byte[] hash = handler.doHash(request.getPassword());


        String login = request.getLogin();

        if (!handler.checkToken(login, hash)) {
            return Response.ok(null).build();
        }

        Account account = Account.find("login", login).firstResult();

        //PersonMainData data = account.main_data;
        List<data> list = new ArrayList<>();

        data response = new  data();
        response.setName(account.main_data.Name);
        response.setSurname(account.main_data.Surname);
        response.setRole(account.role.name);
        list.add(response);

        ResponseAllData dataList = new ResponseAllData();
        dataList.setDataList(list);

        //List<Deal> dealList = Deal.list("status = ?1 and account_id = ?2", true, account.id);
        return Response.ok(dataList).build();


    }

    @Path("/roles")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response showFreeBoxList() {
        List<Role> list = Role.listAll();
        ResRoles resRoles= new ResRoles();
        resRoles.setRoleList(list);
        return Response.ok(resRoles).build();

    }

    @Path("/change")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createNewDeal(RequestChange request) throws NoSuchAlgorithmException {

        byte[] hash = handler.doHash(request.getPassword());

        Account account = Account.find("login = ?1 and password_hash = ?2",
                request.getLogin(), hash).firstResult();





        if (account == null) {
            return null;
        }
        account.main_data.Name = request.getNewName();
        account.main_data.Surname = request.getNewSurname();

        account.persist();




        return Response.ok(true).build();
    }


}
