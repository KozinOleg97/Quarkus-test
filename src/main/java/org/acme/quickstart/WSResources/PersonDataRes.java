package org.acme.quickstart.WSResources;

import org.acme.quickstart.Beans.Deals.RequestDealList;
import org.acme.quickstart.Beans.PersonMainData.RequestAllData;
import org.acme.quickstart.Beans.PersonMainData.ResponseAllData;
import org.acme.quickstart.Beans.PersonMainData.data;
import org.acme.quickstart.Core.LoginHandler;
import org.acme.quickstart.Entity.Account;
import org.acme.quickstart.Entity.Deal;
import org.acme.quickstart.Entity.PersonMainData;
import org.acme.quickstart.Entity.Role;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
}
