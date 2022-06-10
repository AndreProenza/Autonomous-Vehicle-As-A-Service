package avaas.reactive.resource;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.annotations.Param;

import avaas.reactive.repository.APilotDeveloper;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Path("apilot_developer")
public class APilotDeveloperResource {
	
	@Inject
	io.vertx.mutiny.mysqlclient.MySQLPool client;
	
	@GET
	@Path("/all")
	public Multi<APilotDeveloper> get() {
		return APilotDeveloper.findAll(client);
	}
	
	@GET
	@Path("{brand}")
	public Uni<Response> getSingle(@Param String brand) {
		return APilotDeveloper.findById(client, brand)
				.onItem().transform(aPilotDeveloper -> aPilotDeveloper != null ? Response.ok(aPilotDeveloper) :
					Response.status(Status.NOT_FOUND))
				.onItem().transform(ResponseBuilder::build);
	}
	
	@POST
	public Uni<Response> create(APilotDeveloper aPilotDeveloper) {
		return aPilotDeveloper.save(client)
				.onItem().transform(brand -> URI.create("/apilot_developer/" + brand))
				.onItem().transform(uri -> Response.created(uri).build());
	}
	
	@DELETE
	@Path("{brand}")
	public Uni<Response> delete(@Param String brand) {
		return APilotDeveloper.delete(client, brand)
				.onItem().transform(deleted -> deleted ? Status.NO_CONTENT : Status.NOT_FOUND)
				.onItem().transform(status -> Response.status(status).build());
	}
	
	//@PUT
	//@Path("/{brand}")
	public Uni<Response> updateBrand(@Param String brand) {
		return APilotDeveloper.updateBrand(client, brand)
				.onItem().transform(updated -> updated ? Status.NO_CONTENT : Status.NOT_FOUND)
				.onItem().transform(status -> Response.status(status).build());
	}
}
