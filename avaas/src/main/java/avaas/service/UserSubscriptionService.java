package avaas.service;

import java.net.URI;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.annotations.Param;

//import avaas.jdbc.model.PurchaseInfo;
//import avaas.jdbc.service.PurchaseInfoService;
import avaas.reactive.repository.User;
import io.smallrye.mutiny.Uni;

@Path("subscription")
public class UserSubscriptionService {
	
	@Inject
	io.vertx.mutiny.mysqlclient.MySQLPool client;
	
//	private final PurchaseInfoService purchaseInfoService;
//
//    @Inject
//    public UserSubscriptionService(PurchaseInfoService purchaseInfoService) {
//        this.purchaseInfoService = purchaseInfoService;
//    }
	
	@POST
	@Path("/subscribe/user")
	public Uni<Response> create(User user) {
		
		if(!isUserValid(user)) {
			String msg = "Error subscribing user\n"
					+ "User id must be greater than 0\n"
					+ "User Name size must be between 0 and 30 chars\n"
					+ "User Age must be between 18 and 120 years old";
			return Uni.createFrom().item(() -> Response.status(Response.Status.ACCEPTED).entity(msg).build());
		}
		
		
		return user.save(client)
				.onItem().transform(id -> URI.create("/subscription/subscribe/user/" + id))
				.onItem().transform(uri -> Response.created(uri).build())
				.onFailure().recoverWithUni(Uni.createFrom().item(() 
						-> Response.status(Response.Status.ACCEPTED)
						.entity("User already exists. subscribe another user id").build()));
	}
	
	
	
	@DELETE
	@Path("/unsubscribe/user/{id}")
	public Uni<Response> delete(@Param Integer id) {
		return User.delete(client, id)
				.onItem().transform(deleted -> deleted ? Status.NO_CONTENT : Status.NOT_FOUND)
				.onItem().transform(status -> Response.status(status).build());
	}
	
	
	private boolean isUserValid(User user) {
		return user.getId() > 0 && user.getName().length() <= 30 && user.getName().length() > 0 &&
				user.age > 18 && user.getAge() <= 120;		
	}
	
//	private Uni<Response> getUser(Integer id) {
//		return User.findById(client, id)
//				.onItem().transform(user -> user != null ? Response.ok(user) :
//					Response.status(Status.NOT_FOUND))
//				.onItem().transform(ResponseBuilder::build);
//	}
}
