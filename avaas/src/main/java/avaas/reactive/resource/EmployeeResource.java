package avaas.reactive.resource;

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

import avaas.reactive.repository.Employee;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Path("employee")
public class EmployeeResource {
	
	@Inject
	io.vertx.mutiny.mysqlclient.MySQLPool client;
	
	@GET
	@Path("/all")
	public Multi<Employee> get() {
		return Employee.findAll(client);
	}
	
	@GET
	@Path("{userId}")
	public Uni<Response> getSingle(@Param Integer userId) {
		return Employee.findById(client, userId)
				.onItem().transform(employee -> employee != null ? Response.ok(employee) :
					Response.status(Status.NOT_FOUND))
				.onItem().transform(ResponseBuilder::build);
	}
	@POST
	public Uni<Response> create(Employee employee) {
		return employee.save(client)
				.onItem().transform(userId -> URI.create("/employee/" + userId))
				.onItem().transform(uri -> Response.created(uri).build());
	}
	@DELETE
	@Path("{userId}")
	public Uni<Response> delete(@Param Integer userId) {
		return Employee.delete(client, userId)
				.onItem().transform(deleted -> deleted ? Status.NO_CONTENT : Status.NOT_FOUND)
				.onItem().transform(status -> Response.status(status).build());
	}
	@PUT
	@Path("/role/{userId}/{role}")
	public Uni<Response> updateRole(@Param Integer userId , @Param String role) {
		return Employee.updateRole(client, userId , role)
				.onItem().transform(updated -> updated ? Status.NO_CONTENT : Status.NOT_FOUND)
				.onItem().transform(status -> Response.status(status).build());
	}
	
}
