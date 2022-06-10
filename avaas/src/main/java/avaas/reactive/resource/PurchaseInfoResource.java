package avaas.reactive.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.annotations.Param;

import avaas.reactive.repository.PurchaseInfo;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Path("purchase")
public class PurchaseInfoResource {
	
	@Inject
	io.vertx.mutiny.mysqlclient.MySQLPool client;
	
	@GET
	@Path("/all")
	public Multi<PurchaseInfo> get() {
		return PurchaseInfo.findAll(client);
	}
	
	@GET
	@Path("{id}")
	public Uni<Response> getSingle(@Param Integer id) {
		return PurchaseInfo.findById(client, id)
				.onItem().transform(user -> user != null ? Response.ok(user) :
					Response.status(Status.NOT_FOUND))
				.onItem().transform(ResponseBuilder::build);
	}

}
