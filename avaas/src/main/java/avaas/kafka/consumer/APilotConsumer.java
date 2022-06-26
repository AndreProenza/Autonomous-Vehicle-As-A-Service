package avaas.kafka.consumer;

import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;
import javax.enterprise.context.ApplicationScoped;

import avaas.kafka.model.APilot;
import avaas.kafka.producer.APilotProducer;
import avaas.kafka.producer.AvResultProducer;


@ApplicationScoped
public class APilotConsumer {

	final Logger logger = Logger.getLogger(APilotConsumer.class);

	@Incoming("apilot")
	public void receiveAv(Record<String, String> record) {

		System.out.println ("-------- APilot Consume message --------");

		logger.infof("Consumed an APilot: Id = " + record.key() + " Value = " + record.value().toString());    		

		APilot apilot = getAPilotFromMessage(record.value());

		//Produce AV Result
		AvResultProducer.MediationToIQEQAQ(apilot);

		System.out.println ("----------------------------------------");
	}

	private APilot getAPilotFromMessage(String msg) {

		APilot apilot = new APilot();

		String[] msgArray = msg.replace("AvEvents [", "").replace("]", "").split(",");

		String timeStamp = msgArray[0].split("=")[1];
		String avId = msgArray[1].split("=")[1];
		String speed = msgArray[2].split("=")[1];
		String applyBrakes = msgArray[3].split("=")[1];
		String batteryLevel = msgArray[4].split("=")[1];
		String chargeCar = msgArray[5].split("=")[1];
		String driverTirenessLevel = msgArray[6].split("=")[1];
		String takeRest = msgArray[7].split("=")[1];
		String location = msgArray[8].split("=")[1];
		String dangerous = msgArray[9].split("=")[1];
		String environmentalLightning = msgArray[10].split("=")[1];
		String spaceVisualization = msgArray[11].split("=")[1];
		String rainConditions = msgArray[12].split("=")[1];
		String fogConditions = msgArray[13].split("=")[1];
		String weatherStatus = msgArray[14].split("=")[1];
		String tractionWheelsLevel = msgArray[15].split("=")[1];
		String avStatus = msgArray[16].split("=")[1];
		
		apilot.setTimeStamp(timeStamp);
		apilot.setAvId(avId);
		apilot.setSpeed(speed);
		apilot.setApplyBrakes(applyBrakes);
		apilot.setBatteryLevel(batteryLevel);
		apilot.setChargeCar(chargeCar);
		apilot.setDriverTirenessLevel(driverTirenessLevel);
		apilot.setTakeRest(takeRest);
		apilot.setLocation(location);
		apilot.setDangerous(dangerous);
		apilot.setEnvironmentalLightning(environmentalLightning);
		apilot.setSpaceVisualization(spaceVisualization);
		apilot.setRainConditions(rainConditions);
		apilot.setFogConditions(fogConditions);
		apilot.setWeatherStatus(weatherStatus);
		apilot.setTractionWheelsLevel(tractionWheelsLevel);
		apilot.setAvStatus(avStatus);

		return apilot;
	}
}