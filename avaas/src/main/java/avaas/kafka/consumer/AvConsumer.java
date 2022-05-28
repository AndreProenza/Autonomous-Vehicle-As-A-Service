package avaas.kafka.consumer;

import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AvConsumer {
	
	private final Logger logger = Logger.getLogger(AvConsumer.class);

    @Incoming("av-in")
    public void receiveAv(Record<Integer, String> record) {
        logger.infof("Consumed an AV (Autonomous Vehicle): Id = %d | Brand =  %s", record.key(), record.value());
    }
	
}
