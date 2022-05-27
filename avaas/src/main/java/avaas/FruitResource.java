package avaas;

import java.net.URI;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.annotations.Param;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Path("fruits")
public class FruitResource {
	@Inject
	io.vertx.mutiny.mysqlclient.MySQLPool client;
	@Inject
	@ConfigProperty(name = "myapp.schema.create", defaultValue = "true")
	boolean schemaCreate;
	@PostConstruct
	void config() {
		if (schemaCreate) {
			initdb();
		}
	}
	private void initdb() {
		client.query("DROP TABLE IF EXISTS fruits").execute()
		.flatMap(r -> client.query("CREATE TABLE fruits (id SERIAL PRIMARY KEY, name TEXT NOT NULL)").execute())
				.flatMap(r -> client.query("INSERT INTO fruits (name) VALUES ('Orange')").execute())
				.flatMap(r -> client.query("INSERT INTO fruits (name) VALUES ('Pear')").execute())
				.flatMap(r -> client.query("INSERT INTO fruits (name) VALUES ('Apple')").execute())
				.flatMap(r -> client.query("INSERT INTO fruits (name) VALUES ('other')").execute())
				.await().indefinitely();
	}
	@GET
	public Multi<Fruit> get() {
		return Fruit.findAll(client);
	}
	@GET
	@Path("{id}")
	public Uni<Response> getSingle(@Param Long id) {
		return Fruit.findById(client, id)
				.onItem().transform(fruit -> fruit != null ? Response.ok(fruit) :
					Response.status(Status.NOT_FOUND))
				.onItem().transform(ResponseBuilder::build);
	}
	@POST
	public Uni<Response> create(Fruit fruit) {
		return fruit.save(client)
				.onItem().transform(id -> URI.create("/fruits/" + id))
				.onItem().transform(uri -> Response.created(uri).build());
	}
	@DELETE
	@Path("{id}")
	public Uni<Response> delete(@Param Long id) {
		return Fruit.delete(client, id)
				.onItem().transform(deleted -> deleted ? Status.NO_CONTENT : Status.NOT_FOUND)
				.onItem().transform(status -> Response.status(status).build());
	}
	@PUT
	@Path("/{id}/{name}")
	public Uni<Response> update(@Param Long id , @Param String name) {
		return Fruit.update(client, id , name)
				.onItem().transform(updated -> updated ? Status.NO_CONTENT : Status.NOT_FOUND)
				.onItem().transform(status -> Response.status(status).build());
	}
}