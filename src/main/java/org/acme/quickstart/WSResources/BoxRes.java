package org.acme.quickstart.WSResources;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.quickstart.Beans.Box.RequestBoxAdd;
import org.acme.quickstart.Beans.Box.ResponseBoxAdd;
import org.acme.quickstart.Core.BoxHandler;
import org.acme.quickstart.Entity.Box;


import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/box")
public class BoxRes {

    @Inject
    ResponseBoxAdd boxAddRes;
    @Inject
    BoxHandler handler;
   /* @Inject
    SecurityIdentity securityIdentity;


    public class User {

        private final String userName;

        User(SecurityIdentity securityIdentity) {
            this.userName = securityIdentity.getPrincipal().getName();
        }

        public String getUserName() {
            return userName;
        }
    }*/

    @Path("/add")
    //@RolesAllowed("client")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addBox(RequestBoxAdd request) {




        if (handler.checkBoxExist(request.getRow(), request.getCol())) {

            boxAddRes.setResult(false);
            boxAddRes.setComment("already_exist");
            return Response.ok(boxAddRes).build();
        } else {

            handler.addBox(request.getRow(), request.getCol());

            boxAddRes.setResult(true);
            boxAddRes.setComment(null);
            return Response.ok(boxAddRes).build();
        }
    }

    @Path("/all")
    //@RolesAllowed("client")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response showBoxList() {

        return Response.ok(Box.listAll()).build();

    }



    @Path("/free")
    //@RolesAllowed("client")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response showFreeBoxList() {
        List<Box> freeBoxList = Box.list("state", true);
        return Response.ok(freeBoxList).build();

    }
}
