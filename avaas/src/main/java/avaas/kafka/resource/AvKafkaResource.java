package avaas.kafka.resource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import avaas.kafka.producer.AvProducer;
import avaas.reactive.repository.Av;

@Path("kafka/produce/av")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AvKafkaResource {
	
	@Inject
    AvProducer producer;

    @POST
    public Response send(Av av) {
        producer.sendAvToKafka(av);
        // Return an 202 - Accepted response.
        return Response.accepted().build();
    }
}
