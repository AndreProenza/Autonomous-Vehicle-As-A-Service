package avaas.apilot.service.implementation;

import org.springframework.stereotype.Service;

import avaas.apilot.kafka.consumer.APilotKafkaConsumer;
import avaas.apilot.model.APilot;
import avaas.apilot.service.APilotService;

@Service
public class APilotServiceImplementation implements APilotService {
	
	private APilotKafkaConsumer kafka;

	public APilotServiceImplementation(APilotKafkaConsumer kafka) {
		super();
		this.kafka = kafka;
	}

	@Override
	public APilot consumeAPilotFromTopic() {
		return kafka.getAPilot();
	}

}
