package avaas.service;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.annotations.Param;

import avaas.reactive.repository.PurchaseInfo;
import avaas.reactive.repository.User;
import io.smallrye.mutiny.Uni;

@Path("purchase_service")
public class PurchaseService {
	
	@Inject
	io.vertx.mutiny.mysqlclient.MySQLPool client;
	
	@POST
	@Path("/av/buy")
	public Uni<Response> buyAV(PurchaseInfo purchaseInfo) {
		
		if(!isPurchaseServiceValid(purchaseInfo)) {
			String msg = "Error purchasing AV\n"
					+ "Purchase id must be greater than 0\n"
					+ "User id must be greater than 0\n"
					+ "AV id must be greater than 0\n"
					+ "APilot id must be greater than 0";
			return Uni.createFrom().item(() -> Response.status(Response.Status.ACCEPTED).entity(msg).build());
		}
		
		return purchaseInfo.save(client)
				.onItem().transform(id -> URI.create("/purchase_service/av/buy/" + id))
				.onItem().transform(uri -> Response.created(uri).build())
				.onFailure().recoverWithUni(Uni.createFrom().item(() 
						-> Response.status(Response.Status.ACCEPTED)
						.entity("Error purchasing.\n"
								+ "Verify if Purchase id already exists\n"
								+ "Verify if User id exists\n"
								+ "Verify if AV id exists\n"
								+ "Verify if APilot id exists").build()));
	}
	
	private boolean isPurchaseServiceValid(PurchaseInfo purchaseInfo) {
		return purchaseInfo.getId() > 0 && purchaseInfo.getAvId() > 0 && purchaseInfo.getUserId() > 0 && 
				purchaseInfo.getApilotId() > 0;		
	}
	
	
	@PUT
	@Path("/av/sell/{id}")
	public Uni<Response> sellAV(@Param Integer id) {
		return PurchaseInfo.sellAV(client, id)
				.onItem().transform(updated -> updated ? Status.NO_CONTENT : Status.NOT_FOUND)
				.onItem().transform(status -> Response.status(status).build());
	}
	
	
	@PUT
	@Path("/apilot/select/{id}/{apilotId}")
	public Uni<Response> selectAPilot(@Param Integer id , @Param Integer apilotId) {
		return PurchaseInfo.selectAPilot(client, id , apilotId)
				.onItem().transform(updated -> updated ? Status.NO_CONTENT : Status.NOT_FOUND)
				.onItem().transform(status -> Response.status(status).build());
	}
	
	@PUT
	@Path("/apilot/unselect/{id}")
	public Uni<Response> unselectAPilot(@Param Integer id) {
		return PurchaseInfo.unselectAPilot(client, id)
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
