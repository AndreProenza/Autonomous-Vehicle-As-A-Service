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

import avaas.reactive.repository.APilot;
import avaas.reactive.repository.Av;
import io.smallrye.mutiny.Uni;

@Path("apilot_service")
public class APilotDeveloperService {
	
	@Inject
	io.vertx.mutiny.mysqlclient.MySQLPool client;
	
	@POST
	@Path("/enter/apilot")
	public Uni<Response> create(APilot aPilot) {
		
		if(!isAPilotValid(aPilot)) {
			String msg = "Error Adding APilot to the catalog\n"
					+ "APilot id must be greater than 0\n"
					+ "Brand size must be between 0 and 30 chars\n"
					+ "Model size must be between 0 and 30 chars";
			return Uni.createFrom().item(() -> Response.status(Response.Status.ACCEPTED).entity(msg).build());
		}
		
		return aPilot.save(client)
				.onItem().transform(id -> URI.create("/apilot_service/enter/apilot/" + id))
				.onItem().transform(uri -> Response.created(uri).build())
				.onFailure().recoverWithUni(Uni.createFrom().item(() 
						-> Response.status(Response.Status.ACCEPTED)
						.entity("Error invalid data\nAPilot already exists. Add another APilot id"
								+ "\nOR\nBrand invalid\nOR\nRepeated Model").build()));
	}
	
	private boolean isAPilotValid(APilot aPilot) {
		return aPilot.getId() > 0 && aPilot.getBrand().length() > 0 && aPilot.getBrand().length() <= 30 
				&& aPilot.getModel().length() > 0 && aPilot.getModel().length() <= 30;	
	}
	
	
	@DELETE
	@Path("/remove/apilot/{id}")
	public Uni<Response> delete(@Param Integer id) {
		return APilot.delete(client, id)
				.onItem().transform(deleted -> deleted ? Status.NO_CONTENT : Status.NOT_FOUND)
				.onItem().transform(status -> Response.status(status).build());
	}
//	@PUT
//	@Path("/update/apilot/brand/{id}/{brand}")
//	public Uni<Response> updateBrand(@Param Integer id , @Param String brand) {
//		return APilot.updateBrand(client, id , brand)
//				.onItem().transform(updated -> updated ? Status.NO_CONTENT : Status.NOT_FOUND)
//				.onItem().transform(status -> Response.status(status).build());
//	}
	
	@PUT
	@Path("/update/apilot/model/{id}/{model}")
	public Uni<Response> updateModel(@Param Integer id , @Param String model) {
		return APilot.updateModel(client, id , model)
				.onItem().transform(updated -> updated ? Status.NO_CONTENT : Status.NOT_FOUND)
				.onItem().transform(status -> Response.status(status).build());
	}

}
