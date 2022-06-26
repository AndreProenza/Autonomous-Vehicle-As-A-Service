package avaas.kafka.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import avaas.kafka.model.APilot;
import avaas.kafka.model.AvResult;
import avaas.openweathermap.OpenWeatherMapAPI;

public class AvResultProducer {

	final static String kafkaTopic = "av-result";
	static String brokerList = "localhost:9092";

	static void SendMessage(String msg ,  KafkaProducer<String, String> prd , String topicTarget)
	{		
		String seqkey = kafkaTopic + "_" + String.valueOf( ((Double) (Math.random() * 10)).intValue());
		System.out.println("Sending new message to Kafka topic=" + kafkaTopic + " with key=" + seqkey);	
		ProducerRecord<String, String> record = new ProducerRecord<>(topicTarget, seqkey, msg);		
		prd.send(record);
		System.out.println("AV Result Producer sent message...");
	}

	public static void produceMessage(String msg) {
		Properties kafkaProps = new Properties();

		System.out.println ("------ AV Result Producing message -----");

		kafkaProps.put("bootstrap.servers", brokerList); 
		kafkaProps.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer"); 
		kafkaProps.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer"); 
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(kafkaProps);

		SendMessage(msg, producer, kafkaTopic);


	}

	public static void MediationToIQEQAQ(APilot apilot) {

		AvResult avResult = new AvResult();

		String location = apilot.getLocation();
		if(!location.equals("Unknown")) {

			String[] coord = location.split(" ");
			String latitude = coord[0];
			String longitude = coord[1];
			
			System.out.println ("________ Consuming Open Weather Map API ________");
			
			System.out.println("AV Location: " + apilot.getLocation());
			
			System.out.println("Latitude: " + latitude);
			System.out.println("Latitude: " + longitude);

			//Get Weather data given the av location
			String weatherData = OpenWeatherMapAPI
					.processWeatherData(OpenWeatherMapAPI.getWeatherData(latitude, longitude));
			
			String[] msgArray = weatherData.split(",");
			String mainWeather = msgArray[0];
			String description = msgArray[1];
			
			System.out.println("Group of weather parameters: " + mainWeather);
			System.out.println("Weather condition within the group: " + description);
			
			if(mainWeather.equals("Clouds") || description.equals("broken clouds")) {
				apilot.setWeatherStatus("Bad");
			}
			System.out.println ("________________________________________________\n");
		}
		else {
			System.out.println ("________ Consuming Open Weather Map API ________");
			
			System.out.println("AV Location: " + apilot.getLocation());
			System.out.println("Impossible to consume weather from an unknown location");
			
			System.out.println ("________________________________________________\n");
		}



		avResult.setTimeStamp(apilot.getTimeStamp());
		avResult.setAvId(apilot.getAvId());
		avResult.setSpeed(apilot.getSpeed());

		if(apilot.getRainConditions().equals("Heavy Rain") 
				|| apilot.getFogConditions().equals("Dense Fog")) {
			avResult.setIctInfrastructure("Weak");
		} else {
			avResult.setIctInfrastructure("Strong");
		}
		if(apilot.getWeatherStatus().equals("Bad")) {
			avResult.setRoadConditions("Bad Conditions");
		} else {
			avResult.setRoadConditions("Good Conditions");
		}
		if(apilot.getDangerous().equals("Yes")) {
			avResult.setSecurity("Unsecure zone");
		} else {
			avResult.setSecurity("Secure zone");
		}
		if(apilot.getTakeRest().equals("Yes")) {
			avResult.setUtilitarianism("Good action as it promotes happiness");
		} else {
			avResult.setUtilitarianism("Bad action as it doesn't promote happiness");
		}
		if(apilot.getApplyBrakes().equals("Yes")) {
			avResult.setLegitimacy("Illegal speed above allowed speed");
		} else {
			avResult.setLegitimacy("Legal speed within the allowed speed");
		}
		if(apilot.getTakeRest().equals("Yes")) {
			avResult.setSocialResponsibility("Responsible if accident occurs");
		} else {
			avResult.setSocialResponsibility("Not Responsible if accident occurs");
		}
		if(apilot.getSpaceVisualization().equals("Bad")) {
			avResult.setDetection("Weak Space Visualization reduced");
		} else {
			avResult.setDetection("Strong Space Visualization augmented");
		}
		if(apilot.getWeatherStatus().equals("Bad")) {
			avResult.setIdentification("Unidentifiable Space");
		} else {
			avResult.setIdentification("Identifiable Space");
		}
		if(apilot.getDangerous().equals("Yes") || apilot.getWeatherStatus().equals("Bad")) {
			avResult.setRiskAnalysis("Medium risk");
		} 
		else if(apilot.getDangerous().equals("Yes") && apilot.getWeatherStatus().equals("Bad")) {
			avResult.setRiskAnalysis("High risk");
		} else {
			avResult.setRiskAnalysis("Low risk");
		}
		if(apilot.getWeatherStatus().equals("Bad")) {
			avResult.setReaction("Slow");
		} else {
			avResult.setReaction("Fast");
		}
		if(apilot.getAvStatus().equals("Slow Down")) {
			avResult.setExecution("Better Slow Down");
		} else {
			avResult.setExecution("May Continue");
		}

		//		System.out.println(avResult.toString());

		produceMessage(avResult.toString());
	}



}




















