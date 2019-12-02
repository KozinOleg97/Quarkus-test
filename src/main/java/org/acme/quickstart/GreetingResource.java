package org.acme.quickstart;

import org.acme.quickstart.POJO.RequestClient;
import org.acme.quickstart.POJO.ResponceClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class GreetingResource{

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Fine!";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/doLogin")
    public Response getClient(RequestClient requestClient){
        ResponceClient responceClient = new ResponceClient();

        String login = requestClient.getLogin();
        String password = requestClient.getPassword();

        String token = login + "@@@" + password;
        String nameClient = "Denis Sokolov";
        responceClient.setNameClient(nameClient);
        responceClient.setToken(token);


        return Response.ok(responceClient).build();

    }

}