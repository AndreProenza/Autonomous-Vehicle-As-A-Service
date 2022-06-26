package avaas.kafka.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import avaas.kafka.model.APilot;
import avaas.kafka.model.AvEvents;

public class APilotProducer {

	private final static String kafkaTopic = "apilot";
	private static String brokerList = "localhost:9092";

	private static void SendMessage(String msg ,  KafkaProducer<String, String> prd , String topicTarget)
	{		
		String seqkey = kafkaTopic + "_" + String.valueOf( ((Double) (Math.random() * 10)).intValue());
		System.out.println("Sending new message to Kafka topic=" + kafkaTopic + " with key=" + seqkey);	
		ProducerRecord<String, String> record = new ProducerRecord<>(topicTarget, seqkey, msg);		
		prd.send(record);
		System.out.println("APilot Producer sent message...");
	}

	public static void produceMessage(String msg) {
		Properties kafkaProps = new Properties();

		System.out.println ("------- APilot Producing message -------");

		kafkaProps.put("bootstrap.servers", brokerList); 
		kafkaProps.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer"); 
		kafkaProps.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer"); 
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(kafkaProps);
		
		SendMessage(msg, producer, kafkaTopic);


	}
	
	public static void processAPilotFunctionality(AvEvents avEvents) {
				
		APilot apilot = new APilot();
		
		apilot.setTimeStamp(avEvents.getTimeStamp());
		apilot.setAvId(avEvents.getAvId());
		apilot.setSpeed(avEvents.getSpeed());
		if(Integer.parseInt(avEvents.getSpeed()) > 50) {
			apilot.setApplyBrakes("Yes");
		} else {
			apilot.setApplyBrakes("No");
		}
		apilot.setBatteryLevel(avEvents.getBatteryLevel());
		if(Integer.parseInt(avEvents.getBatteryLevel()) < 20) {
			apilot.setChargeCar("Yes");
		} else {
			apilot.setChargeCar("No");
		}
		apilot.setDriverTirenessLevel(avEvents.getDriverTirenessLevel());
		if(Integer.parseInt(avEvents.getDriverTirenessLevel()) > 70) {
			apilot.setTakeRest("Yes");
		} else {
			apilot.setTakeRest("No");
		}
		apilot.setLocation(avEvents.getLocation());
		if(avEvents.getLocation().equals("Unknown")) {
			apilot.setDangerous("Yes");
		} else {
			apilot.setDangerous("No");
		}
		apilot.setEnvironmentalLightning(avEvents.getEnvironmentalLightning());
		if(avEvents.getEnvironmentalLightning().equals("N/A") || avEvents.getEnvironmentalLightning().equals("Bad")) {
			apilot.setSpaceVisualization("Bad");
		} else {
			apilot.setSpaceVisualization("Good");
		}
		apilot.setRainConditions(avEvents.getRainConditions());
		apilot.setFogConditions(avEvents.getFogConditions());
		if(avEvents.getRainConditions().equals("Heavy Rain") || avEvents.getFogConditions().equals("Dense Fog")) {
			apilot.setWeatherStatus("Bad");
		} else {
			apilot.setWeatherStatus("Good");
		}
		apilot.setTractionWheelsLevel(avEvents.getTractionWheelsLevel());
		if(Integer.parseInt(avEvents.getSpeed()) > 50 || Integer.parseInt(avEvents.getBatteryLevel()) < 20 || 
				Integer.parseInt(avEvents.getDriverTirenessLevel()) > 70 || avEvents.getLocation().equals("Unknown") ||
				avEvents.getEnvironmentalLightning().equals("N/A") || avEvents.getEnvironmentalLightning().equals("Bad") ||
				avEvents.getRainConditions().equals("Heavy Rain") || avEvents.getFogConditions().equals("Dense Fog") || 
				Integer.parseInt(avEvents.getTractionWheelsLevel()) < 20) {
			apilot.setAvStatus("Slow Down");
		} else {
			apilot.setAvStatus("Continue");
		}
		
		//Send Message
		APilotProducer.produceMessage(apilot.toString());
	}

}
