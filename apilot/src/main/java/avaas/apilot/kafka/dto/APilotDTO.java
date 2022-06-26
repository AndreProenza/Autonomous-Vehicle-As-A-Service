package avaas.apilot.kafka.dto;

import avaas.apilot.model.APilot;

public class APilotDTO {
	
	public static APilot apilot;

	public static APilot getApilot() {
		return apilot;
	}

	public static void setApilot(APilot apilot) {
		APilotDTO.apilot = apilot;
	}

}
