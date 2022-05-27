package avaas;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.annotations.Param;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Path("car_manufacturer")
public class CarManufacturerResource {
	
	@Inject
	io.vertx.mutiny.mysqlclient.MySQLPool client;
	
	@GET
	@Path("/all")
	public Multi<CarManufacturer> get() {
		return CarManufacturer.findAll(client);
	}
	
	@GET
	@Path("{brand}")
	public Uni<Response> getSingle(@Param String brand) {
		return CarManufacturer.findById(client, brand)
				.onItem().transform(carManufacturer -> carManufacturer != null ? Response.ok(carManufacturer) :
					Response.status(Status.NOT_FOUND))
				.onItem().transform(ResponseBuilder::build);
	}
	
	@POST
	public Uni<Response> create(CarManufacturer carManufacturer) {
		return carManufacturer.save(client)
				.onItem().transform(brand -> URI.create("/car_manufacturer/" + brand))
				.onItem().transform(uri -> Response.created(uri).build());
	}
	
	@DELETE
	@Path("{brand}")
	public Uni<Response> delete(@Param String brand) {
		return CarManufacturer.delete(client, brand)
				.onItem().transform(deleted -> deleted ? Status.NO_CONTENT : Status.NOT_FOUND)
				.onItem().transform(status -> Response.status(status).build());
	}
	
	//@PUT
	//@Path("/{brand}")
	public Uni<Response> updateBrand(@Param String brand) {
		return CarManufacturer.updateBrand(client, brand)
				.onItem().transform(updated -> updated ? Status.NO_CONTENT : Status.NOT_FOUND)
				.onItem().transform(status -> Response.status(status).build());
	}
	
}
