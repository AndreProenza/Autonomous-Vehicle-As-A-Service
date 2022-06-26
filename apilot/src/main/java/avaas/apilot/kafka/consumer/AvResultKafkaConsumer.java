package avaas.apilot.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import avaas.apilot.kafka.dto.AvResultDTO;
import avaas.apilot.model.AvResult;

@Service
public class AvResultKafkaConsumer {

	@KafkaListener(topics="av-result", groupId="apilot")
	public void consumeFromTopic(String msg) {

		System.out.println("Consumed AV Result = " + msg);

		AvResult avResult = new AvResult();

		String[] msgArray = msg.replace("AvResult [", "").replace("]", "").split(",");

		String timeStamp = msgArray[0].split("=")[1];
		String avId = msgArray[1].split("=")[1];
		String speed = msgArray[2].split("=")[1];
		String ictInfrastructure = msgArray[3].split("=")[1];
		String roadConditions = msgArray[4].split("=")[1];
		String utilitarism = msgArray[5].split("=")[1];
		String legitimacy = msgArray[6].split("=")[1];
		String socialResponsability = msgArray[7].split("=")[1];
		String detection = msgArray[8].split("=")[1];
		String identification = msgArray[9].split("=")[1];
		String riskAnalysis = msgArray[10].split("=")[1];
		String reaction = msgArray[11].split("=")[1];
		String execution = msgArray[12].split("=")[1];
		
		avResult.setTimeStamp(timeStamp);
		avResult.setAvId(avId);
		avResult.setSpeed(speed);
		avResult.setIctInfrastructure(ictInfrastructure);
		avResult.setRoadConditions(roadConditions);
		avResult.setUtilitarism(utilitarism);
		avResult.setLegitimacy(legitimacy);
		avResult.setSocialResponsability(socialResponsability);
		avResult.setDetection(detection);
		avResult.setIdentification(identification);
		avResult.setRiskAnalysis(riskAnalysis);
		avResult.setReaction(reaction);
		avResult.setExecution(execution);
		
		System.out.println(avResult.toString());
	
		AvResultDTO.setAvResult(avResult);
	}

	public AvResult getAvResult() {
//		APilot apilot = new APilot("timeStamp", "avId", "speed", "ictInfrastructure", "roadConditions",
//				"utilitarism", "legitimacy", "socialResponsability", "detection", "identification",
//				"riskAnalysis", "reaction", "execution");
//		APilotDTO.setApilot(apilot);
		return AvResultDTO.getAvResult();
	}

}
