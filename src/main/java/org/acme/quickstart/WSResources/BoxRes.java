package org.acme.quickstart.WSResources;


import org.acme.quickstart.Beans.Box.RequestBoxAdd;
import org.acme.quickstart.Beans.Box.RequestBoxRemove;
import org.acme.quickstart.Core.BoxHandler;
import org.acme.quickstart.Entity.Box;
import org.jboss.logging.Logger;

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
    private static final Logger LOG = Logger.getLogger(BoxRes.class);


    @Inject
    BoxHandler handler;


    @Path("/add")
    @RolesAllowed("admin")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addBox(RequestBoxAdd request) {
        try {
            if (handler.checkBoxExist(request.getRow(), request.getCol())) {

                return Response.status(400).build();
            } else {

                if (request.getCoefficient() == null) {
                    handler.addBox(request.getRow(), request.getCol(), 1);
                } else {
                    handler.addBox(request.getRow(), request.getCol(), request.getCoefficient());
                }

                return Response.ok().build();
            }
        } catch (Exception e) {
            LOG.error("Server error (box/add)", e);
            return Response.status(500).build();
        }
    }

    @Path("/remove")
    @RolesAllowed("admin")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response removeBox(RequestBoxRemove request) {
        try {

            Box box = Box.findById(request.getBox_id());
            if (!box.occupied) {
                box.delete();
                return Response.ok().build();
            } else
                return Response.status(400).build();

        } catch (Exception e) {
            LOG.error("Server error (box/remove)", e);
            return Response.status(500).build();
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
        List<Box> list = Box.list("occupied", false);
        return Response.ok(list).build();

    }
}
