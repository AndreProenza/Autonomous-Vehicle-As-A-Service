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

import avaas.reactive.repository.Av;
import io.smallrye.mutiny.Uni;

@Path("car_manufacturer_service")
public class CarManufacturerService {
	
	@Inject
	io.vertx.mutiny.mysqlclient.MySQLPool client;
	
	
	@POST
	@Path("/enter/av")
	public Uni<Response> create(Av av) {
		
		if(!isAvValid(av)) {
			String msg = "Error Adding AV to the catalog\n"
					+ "AV id must be greater than 0\n"
					+ "Brand size must be between 0 and 30 chars\n"
					+ "Model size must be between 0 and 30 chars";
			return Uni.createFrom().item(() -> Response.status(Response.Status.ACCEPTED).entity(msg).build());
		}
		
		return av.save(client)
				.onItem().transform(id -> URI.create("/car_manufacturer_service/enter/av/" + id))
				.onItem().transform(uri -> Response.created(uri).build())
				.onFailure().recoverWithUni(Uni.createFrom().item(() 
						-> Response.status(Response.Status.ACCEPTED)
						.entity("Error invalid data\nAV already exists. Add another AV id"
								+ "\nOR\nBrand invalid\nOR\nRepeated Model").build()));
	}
	
	private boolean isAvValid(Av av) {
		return av.getId() > 0 && av.getBrand().length() > 0 && av.getBrand().length() <= 30 
				&& av.getModel().length() > 0 && av.getModel().length() <= 30;		
	}
	
	
	@DELETE
	@Path("remove/av/{id}")
	public Uni<Response> delete(@Param Integer id) {
		return Av.delete(client, id)
				.onItem().transform(deleted -> deleted ? Status.NO_CONTENT : Status.NOT_FOUND)
				.onItem().transform(status -> Response.status(status).build());
	}
//	@PUT
//	@Path("/update/av/brand/{id}/{brand}")
//	public Uni<Response> updateBrand(@Param Integer id , @Param String brand) {
//		return Av.updateBrand(client, id , brand)
//				.onItem().transform(updated -> updated ? Status.NO_CONTENT : Status.NOT_FOUND)
//				.onItem().transform(status -> Response.status(status).build());
//	}
	
	@PUT
	@Path("/update/av/model/{id}/{model}")
	public Uni<Response> updateModel(@Param Integer id , @Param String model) {
		return Av.updateModel(client, id , model)
				.onItem().transform(updated -> updated ? Status.NO_CONTENT : Status.NOT_FOUND)
				.onItem().transform(status -> Response.status(status).build());
	}
	
	
//	private Uni<Response> getAV(Integer id) {
//		return Av.findById(client, id)
//				.onItem().transform(av -> av != null ? Response.ok(av) :
//					Response.status(Status.NOT_FOUND))
//				.onItem().transform(ResponseBuilder::build);
//	}
	
}
