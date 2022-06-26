package avaas.kafka.consumer;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import avaas.kafka.model.AvEvents;
import avaas.kafka.producer.APilotProducer;
import io.smallrye.reactive.messaging.kafka.Record;

public class AvEventConsumer {

	final Logger logger = Logger.getLogger(AvEventConsumer.class);

	private String timeStamp;
	private String avId;
	private String speed;
	private String batteryLevel;
	private String driverTirenessLevel;
	private String location;
	private String environmentalLightning;
	private String rainConditions;
	private String fogConditions;
	private String tractionWheelsLevel;

	@Incoming("av-event")
	public void receiveAv(Record<Integer, String> record) {
		
		System.out.println ("------- AV Event Consume message -------");

		logger.infof("Consumed an AV_Event: Id = " +  record.key() + " Value =  " + record.value());
		
		processAvEventMessage(record.value());

		AvEvents avEvents = new AvEvents(timeStamp, avId, speed, batteryLevel, driverTirenessLevel, location, 
				environmentalLightning, rainConditions, fogConditions, tractionWheelsLevel);

		
		APilotProducer.processAPilotFunctionality(avEvents);
		
		System.out.println ("----------------------------------------");
	}
	
		
	
	private void processAvEventMessage(String msg) {
		String[] msgArray = msg.replace("{\"AV_Event\":{", "").replace("{", "").replace("}", "").replace("\"", "").split(",");

		timeStamp = msgArray[0].replace("TimeStamp:", "");
		avId = msgArray[1].split(":")[1];
		speed = msgArray[2].split(":")[1];
		batteryLevel = msgArray[3].split(":")[1];
		driverTirenessLevel = msgArray[4].split(":")[1];
		location = msgArray[5].split(":")[1];
		environmentalLightning = msgArray[6].split(":")[1];
		rainConditions = msgArray[7].split(":")[1];
		fogConditions = msgArray[8].split(":")[1];
		tractionWheelsLevel = msgArray[9].split(":")[1];

	}
	
	
}
