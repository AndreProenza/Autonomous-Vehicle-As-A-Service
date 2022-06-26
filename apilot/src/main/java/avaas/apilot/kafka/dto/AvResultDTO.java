package avaas.apilot.kafka.dto;

import avaas.apilot.model.AvResult;

public class AvResultDTO {
	
	public static AvResult avResult;

	public static AvResult getAvResult() {
		return avResult;
	}

	public static void setAvResult(AvResult avResult) {
		AvResultDTO.avResult = avResult;
	}

	
}
