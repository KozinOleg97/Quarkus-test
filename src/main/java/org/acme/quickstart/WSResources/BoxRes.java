package org.acme.quickstart.WSResources;

import org.acme.quickstart.Beans.Box.RequestBoxAdd;
import org.acme.quickstart.Beans.Box.ResponseBoxAdd;
import org.acme.quickstart.Core.BoxHandler;
import org.acme.quickstart.Entity.Box;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/box")
public class BoxRes {

    @Inject
    ResponseBoxAdd boxAddRes;
    @Inject
    BoxHandler handler;

    @Path("/add")
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
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response showBoxList() {

        return Response.ok(Box.listAll()).build();

    }
}
