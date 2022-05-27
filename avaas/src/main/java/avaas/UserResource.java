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

@Path("user")
public class UserResource {
	
	@Inject
	io.vertx.mutiny.mysqlclient.MySQLPool client;
	
	@GET
	@Path("/all")
	public Multi<User> get() {
		return User.findAll(client);
	}
	
	@GET
	@Path("{id}")
	public Uni<Response> getSingle(@Param Integer id) {
		return User.findById(client, id)
				.onItem().transform(user -> user != null ? Response.ok(user) :
					Response.status(Status.NOT_FOUND))
				.onItem().transform(ResponseBuilder::build);
	}
	@POST
	public Uni<Response> create(User user) {
		return user.save(client)
				.onItem().transform(id -> URI.create("/user/" + id))
				.onItem().transform(uri -> Response.created(uri).build());
	}
	@DELETE
	@Path("{id}")
	public Uni<Response> delete(@Param Integer id) {
		return User.delete(client, id)
				.onItem().transform(deleted -> deleted ? Status.NO_CONTENT : Status.NOT_FOUND)
				.onItem().transform(status -> Response.status(status).build());
	}
	@PUT
	@Path("/name/{id}/{name}")
	public Uni<Response> updateBrand(@Param Integer id , @Param String name) {
		return User.updateName(client, id , name)
				.onItem().transform(updated -> updated ? Status.NO_CONTENT : Status.NOT_FOUND)
				.onItem().transform(status -> Response.status(status).build());
	}
	
	@PUT
	@Path("/age/{id}/{age}")
	public Uni<Response> updateModel(@Param Integer id , @Param int age) {
		return User.updateAge(client, id , age)
				.onItem().transform(updated -> updated ? Status.NO_CONTENT : Status.NOT_FOUND)
				.onItem().transform(status -> Response.status(status).build());
	}
}
