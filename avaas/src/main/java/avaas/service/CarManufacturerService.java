package avaas.service;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.annotations.Param;

import avaas.repository.Av;
import avaas.repository.CarManufacturer;
import io.smallrye.mutiny.Uni;

@Path("car_manufacturer_service")
public class CarManufacturerService {
	
	@Inject
	io.vertx.mutiny.mysqlclient.MySQLPool client;
	
	
	@POST
	@Path("/enter/av")
	public Uni<Response> create(Av av) {
		
//		if(getAV(av.getId()) != null) {
//			String msg = "AV already exists. Enter another AV id";
//			return Uni.createFrom().item(() -> Response.status(Response.Status.ACCEPTED).entity(msg).build());
//		}
		
		return av.save(client)
				.onItem().transform(id -> URI.create("/car_manufacturer_service/enter/av/" + id))
				.onItem().transform(uri -> Response.created(uri).build());
	}
	@DELETE
	@Path("remove/av/{id}")
	public Uni<Response> delete(@Param Integer id) {
		return Av.delete(client, id)
				.onItem().transform(deleted -> deleted ? Status.NO_CONTENT : Status.NOT_FOUND)
				.onItem().transform(status -> Response.status(status).build());
	}
	@PUT
	@Path("/update/av/brand/{id}/{brand}")
	public Uni<Response> updateBrand(@Param Integer id , @Param String brand) {
		return Av.updateBrand(client, id , brand)
				.onItem().transform(updated -> updated ? Status.NO_CONTENT : Status.NOT_FOUND)
				.onItem().transform(status -> Response.status(status).build());
	}
	
	@PUT
	@Path("/update/av/model/{id}/{model}")
	public Uni<Response> updateModel(@Param Integer id , @Param String model) {
		return Av.updateModel(client, id , model)
				.onItem().transform(updated -> updated ? Status.NO_CONTENT : Status.NOT_FOUND)
				.onItem().transform(status -> Response.status(status).build());
	}
	
	
	private Uni<Response> getAV(Integer id) {
		return Av.findById(client, id)
				.onItem().transform(av -> av != null ? Response.ok(av) :
					Response.status(Status.NOT_FOUND))
				.onItem().transform(ResponseBuilder::build);
	}
	
}
