package org.acme.quickstart;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.quickstart.Entity.Qwe;
import org.acme.quickstart.Entity.Role;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/test")
public class Test {



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PanacheEntityBase> getTest() {

        return Role.listAll();

    }

    @Transactional
    @GET
    @Path("/qwe")
    @Produces(MediaType.APPLICATION_JSON)
    public Qwe test2(){
        Qwe qwe = new Qwe();
        qwe.name="qqqqq2222qqqqqqqqqqq1";
        qwe.persist();

        return qwe;
    }

}
