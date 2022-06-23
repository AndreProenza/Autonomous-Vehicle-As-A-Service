package avaas.kafka.model;

public class AvEvents {
	
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
	
	public AvEvents() {
		
    }

	public AvEvents(String timeStamp, String avId, String speed, String batteryLevel, String driverTirenessLevel,
			String location, String environmentalLightning, String rainConditions, String fogConditions,
			String tractionWheelsLevel) {
		super();
		this.timeStamp = timeStamp;
		this.avId = avId;
		this.speed = speed;
		this.batteryLevel = batteryLevel;
		this.driverTirenessLevel = driverTirenessLevel;
		this.location = location;
		this.environmentalLightning = environmentalLightning;
		this.rainConditions = rainConditions;
		this.fogConditions = fogConditions;
		this.tractionWheelsLevel = tractionWheelsLevel;
	}

	@Override
	public String toString() {
		return "AvEvents [timeStamp=" + timeStamp + ", avId=" + avId + ", speed=" + speed + ", batteryLevel="
				+ batteryLevel + ", driverTirenessLevel=" + driverTirenessLevel + ", location=" + location
				+ ", environmentalLightning=" + environmentalLightning + ", rainConditions=" + rainConditions
				+ ", fogConditions=" + fogConditions + ", tractionWheelsLevel=" + tractionWheelsLevel + "]";
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getAvId() {
		return avId;
	}

	public void setAvId(String avId) {
		this.avId = avId;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(String batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	public String getDriverTirenessLevel() {
		return driverTirenessLevel;
	}

	public void setDriverTirenessLevel(String driverTirenessLevel) {
		this.driverTirenessLevel = driverTirenessLevel;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEnvironmentalLightning() {
		return environmentalLightning;
	}

	public void setEnvironmentalLightning(String environmentalLightning) {
		this.environmentalLightning = environmentalLightning;
	}

	public String getRainConditions() {
		return rainConditions;
	}

	public void setRainConditions(String rainConditions) {
		this.rainConditions = rainConditions;
	}

	public String getFogConditions() {
		return fogConditions;
	}

	public void setFogConditions(String fogConditions) {
		this.fogConditions = fogConditions;
	}

	public String getTractionWheelsLevel() {
		return tractionWheelsLevel;
	}

	public void setTractionWheelsLevel(String tractionWheelsLevel) {
		this.tractionWheelsLevel = tractionWheelsLevel;
	}
	
	

}
