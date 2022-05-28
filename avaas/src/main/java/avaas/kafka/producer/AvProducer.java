package avaas.kafka.producer;

import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import avaas.repository.Av;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AvProducer {
	
	@Inject @Channel("av-out")
    Emitter<Record<Integer, String>> emitter;

    public void sendAvToKafka(Av av) {
        emitter.send(Record.of(av.id, av.brand));
    }
}
