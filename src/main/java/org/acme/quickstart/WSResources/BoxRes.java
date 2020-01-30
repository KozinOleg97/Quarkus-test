package org.acme.quickstart.WSResources;


import org.acme.quickstart.Beans.Box.RequestBoxAdd;
import org.acme.quickstart.Beans.Box.ResponseBoxAdd;
import org.acme.quickstart.Core.BoxHandler;
import org.acme.quickstart.Entity.Box;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
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
    //@Inject
    //ResponseFreeBoxList freeBoxList;


    @Path("/add")
    @RolesAllowed("admin")
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

    @Path("/show/all")
    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response showBoxList() {

        return Response.ok(Box.listAll()).build();

    }


    @Path("/show/free")
    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response showFreeBoxList() {
        List<Box> list = Box.list("occupied", true);
        //freeBoxList.setBoxList(list);
        return Response.ok(list).build();

    }
}
