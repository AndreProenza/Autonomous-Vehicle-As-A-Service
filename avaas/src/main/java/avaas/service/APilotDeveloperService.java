package avaas.service;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.annotations.Param;

import avaas.repository.APilot;
import io.smallrye.mutiny.Uni;

@Path("apilot_service")
public class APilotDeveloperService {
	
	@Inject
	io.vertx.mutiny.mysqlclient.MySQLPool client;
	
	@POST
	@Path("/enter/apilot")
	public Uni<Response> create(APilot aPilot) {
		return aPilot.save(client)
				.onItem().transform(id -> URI.create("/apilot_service/enter/apilot/" + id))
				.onItem().transform(uri -> Response.created(uri).build());
	}
	@DELETE
	@Path("/remove/apilot/{id}")
	public Uni<Response> delete(@Param Integer id) {
		return APilot.delete(client, id)
				.onItem().transform(deleted -> deleted ? Status.NO_CONTENT : Status.NOT_FOUND)
				.onItem().transform(status -> Response.status(status).build());
	}
	@PUT
	@Path("/update/apilot/brand/{id}/{brand}")
	public Uni<Response> updateBrand(@Param Integer id , @Param String brand) {
		return APilot.updateBrand(client, id , brand)
				.onItem().transform(updated -> updated ? Status.NO_CONTENT : Status.NOT_FOUND)
				.onItem().transform(status -> Response.status(status).build());
	}
	
	@PUT
	@Path("/update/apilot/model/{id}/{model}")
	public Uni<Response> updateModel(@Param Integer id , @Param String model) {
		return APilot.updateModel(client, id , model)
				.onItem().transform(updated -> updated ? Status.NO_CONTENT : Status.NOT_FOUND)
				.onItem().transform(status -> Response.status(status).build());
	}

}
