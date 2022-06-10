package avaas.service;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.annotations.Param;

import avaas.repository.PurchaseInfo;
import io.smallrye.mutiny.Uni;

@Path("purchase_service")
public class PurchaseService {
	
	@Inject
	io.vertx.mutiny.mysqlclient.MySQLPool client;
	
	@POST
	@Path("/setup")
	public Uni<Response> setup(PurchaseInfo purchaseInfo) {
		return purchaseInfo.save(client)
				.onItem().transform(id -> URI.create("/purchase_service/setup/" + id))
				.onItem().transform(uri -> Response.created(uri).build());
	}
	
	@PUT
	@Path("/buy/av/{id}/{avId}")
	public Uni<Response> buyAV(@Param Integer id , @Param Integer avId) {
		return PurchaseInfo.updateAV(client, id , avId)
				.onItem().transform(updated -> updated ? Status.NO_CONTENT : Status.NOT_FOUND)
				.onItem().transform(status -> Response.status(status).build());
	}
	
	@PUT
	@Path("/sell/av/{id}/{avId}")
	public Uni<Response> sellAV(@Param Integer id , @Param Integer avId) {
		return PurchaseInfo.updateAV(client, id , avId)
				.onItem().transform(updated -> updated ? Status.NO_CONTENT : Status.NOT_FOUND)
				.onItem().transform(status -> Response.status(status).build());
	}
	
	@PUT
	@Path("/buy/av/{id}/{apilotId}")
	public Uni<Response> buyAPilot(@Param Integer id , @Param Integer apilotId) {
		return PurchaseInfo.updateAPilot(client, id , apilotId)
				.onItem().transform(updated -> updated ? Status.NO_CONTENT : Status.NOT_FOUND)
				.onItem().transform(status -> Response.status(status).build());
	}
	
	@PUT
	@Path("/sell/av/{id}/{apilotId}")
	public Uni<Response> sellAPilot(@Param Integer id , @Param Integer apilotId) {
		return PurchaseInfo.updateAPilot(client, id , apilotId)
				.onItem().transform(updated -> updated ? Status.NO_CONTENT : Status.NOT_FOUND)
				.onItem().transform(status -> Response.status(status).build());
	}

//	@DELETE
//	@Path("/remove/{id}")
//	public Uni<Response> delete(@Param Integer id) {
//		return PurchaseInfo.delete(client, id)
//				.onItem().transform(deleted -> deleted ? Status.NO_CONTENT : Status.NOT_FOUND)
//				.onItem().transform(status -> Response.status(status).build());
//	}
}
