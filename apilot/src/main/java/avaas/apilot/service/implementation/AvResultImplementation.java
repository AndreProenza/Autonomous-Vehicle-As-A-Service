package avaas.apilot.service.implementation;

import org.springframework.stereotype.Service;

import avaas.apilot.kafka.consumer.AvResultKafkaConsumer;
import avaas.apilot.model.AvResult;
import avaas.apilot.service.AvResultService;

@Service
public class AvResultImplementation implements AvResultService {
	
	private AvResultKafkaConsumer kafka;

	public AvResultImplementation(AvResultKafkaConsumer kafka) {
		super();
		this.kafka = kafka;
	}

	@Override
	public AvResult consumeAvResultFromTopic() {
		return kafka.getAvResult();
	}


}
