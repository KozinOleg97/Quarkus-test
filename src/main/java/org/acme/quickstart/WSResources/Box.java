package org.acme.quickstart.WSResources;

import org.acme.quickstart.Beans.Box.RequestBoxAdd;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/box")
public class Box {

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBox(RequestBoxAdd request){

        return null;
    }
}
