package io.tracee.examples.jaxrs2;

import javax.ejb.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import io.tracee.Tracee;

@Singleton
@Path("/tmp")
public class Controller {

    @Path("/multiply")
    @GET
    @Produces("text/plain")
    public long multiply(@QueryParam("attribute1") long attribute1, @QueryParam("attribute2") long attribute2) {
        Tracee.getBackend().getLoggerFactory().getLogger(Controller.class).info("JAX-RS : multiply " + attribute1 + " with " + attribute2);
        return attribute1 * attribute2;
    }

    @Path("/sum")
    @GET
    @Produces("text/plain")
    public long sum(@QueryParam("attribute1") long attribute1, @QueryParam("attribute2") long attribute2) {
        Tracee.getBackend().getLoggerFactory().getLogger(Controller.class).info("JAX-RS : sum " + attribute1 + " with " + attribute2);
        // return Response.status(200).entity(attribute1 + attribute2).build();
        return attribute1 + attribute2;
    }

}
