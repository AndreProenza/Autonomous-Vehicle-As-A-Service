package avaas.kafka.consumer;

import java.util.Arrays;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

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

    @Incoming("av-event-topic")
    public void receiveAv(Record<Integer, String> record) {
    	
    	
    	
    	logger.infof("Got AV_Event: Id = " +  record.key() + " Value =  " + record.value());

    	String msg = record.value();
    	String[] msgArray = msg.replace("{\"AV_Event\":{", "").replace("{", "").replace("}", "").replace("\"", "").split(",");
    	
    	timeStamp = msgArray[0].replace("TimeStamp:", "");
    	avId = msgArray[1].split(":")[1];
    	speed = msgArray[2].split(":")[1];
	   	batteryLevel = msgArray[3].split(":")[1];
	   	driverTirenessLevel = msgArray[4].split(":")[1];
	   	location = msgArray[5].split(":")[1] + msgArray[6];
	   	environmentalLightning = msgArray[7].split(":")[1];
	   	rainConditions = msgArray[8].split(":")[1];
	   	fogConditions = msgArray[9].split(":")[1];
	   	
	   	if(msgArray.length == 11){
	   		tractionWheelsLevel = msgArray[10].split(":")[1];
	   	}else{
	   		tractionWheelsLevel = null;
	   	}
	   	
	   	
	   	//System.out.println(Arrays.toString(msgArray));
    	
    	System.out.println("[ timeStamp: " + timeStamp + ", avId: " + avId + ", speed: " + speed + ", batteryLevel: "  + batteryLevel + ", driverTirenessLevel: " + driverTirenessLevel 
    			+ ", location: " + location + ", environmentalLightning: " + environmentalLightning + ", rainConditions: " + rainConditions + ", fogConditions: " + fogConditions + ", tractionWheelsLevel: " + tractionWheelsLevel + " ]");
    	

    }
}
