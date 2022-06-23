package avaas.kafka.deserializer;

import avaas.kafka.model.AvEvents;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class AvEventDeserializer extends ObjectMapperDeserializer<AvEvents> {
	
    public AvEventDeserializer() {
        super(AvEvents.class);
    }
}