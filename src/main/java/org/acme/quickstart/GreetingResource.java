package org.acme.quickstart;

import io.agroal.api.AgroalDataSource;
import org.acme.quickstart.Core.RegistrationHandler;
import org.acme.quickstart.POJO.RequestClient;
import org.acme.quickstart.POJO.ResponseClient;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/login")
public class GreetingResource{




    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {




        return "Работает!! \n при push этого репа https://github.com/KozinOleg97/Quarkus-test" +
                " автоматом собирается контейнер на докерхабе, дальше (ПОКА вручную) нужно \n" +
                "  на виртуалке скачать образ и запустить (2 комманды)";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/doLogin")
    public Response getClient(RequestClient requestClient){
       /* ResponseClient responceClient = new ResponseClient();

        String login = requestClient.getLogin();
        String password = requestClient.getPassword();

        String token = login + "@@@" + password;
        String nameClient = "Denis Sokolov";
        responceClient.setNameClient(nameClient);
        responceClient.setToken(token);


        return Response.ok(responceClient).build();*/
       return null;

    }

}