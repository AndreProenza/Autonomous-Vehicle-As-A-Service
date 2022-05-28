package avaas.kafka.consumer;

import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import avaas.util.Util;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AvConsumer {
	
	private final Logger logger = Logger.getLogger(AvConsumer.class);

    @Incoming("av-in")
    public void receiveAv(Record<Integer, String> record) {
    	List<String> brandAndModel = Util.StringToList(record.value());
    	
    	logger.infof("Consumed an AV (Autonomous Vehicle): Id = " + record.key() + " | "
    			+ "Brand =  " + brandAndModel.get(0) + " | Model = " + brandAndModel.get(1));
    }
    
    
	
}
