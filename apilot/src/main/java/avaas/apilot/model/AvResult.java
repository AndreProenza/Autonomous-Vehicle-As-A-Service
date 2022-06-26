package avaas.apilot.model;

public class AvResult {
	
	private String timeStamp;
	private String avId;
	private String speed;
	private String ictInfrastructure;
	private String roadConditions;
	private String security;
	private String utilitarism;
	private String legitimacy;
	private String socialResponsability;
	private String detection;
	private String identification;
	private String riskAnalysis;
	private String reaction;
	private String execution;
	
	public AvResult() {
		//Does nothing
	}

	public AvResult(String timeStamp, String avId, String speed, String ictInfrastructure, String roadConditions,
			String security, String utilitarism, String legitimacy, String socialResponsability, String detection,
			String identification, String riskAnalysis, String reaction, String execution) {
		super();
		this.timeStamp = timeStamp;
		this.avId = avId;
		this.speed = speed;
		this.ictInfrastructure = ictInfrastructure;
		this.roadConditions = roadConditions;
		this.security = security;
		this.utilitarism = utilitarism;
		this.legitimacy = legitimacy;
		this.socialResponsability = socialResponsability;
		this.detection = detection;
		this.identification = identification;
		this.riskAnalysis = riskAnalysis;
		this.reaction = reaction;
		this.execution = execution;
	}
	
	

	@Override
	public String toString() {
		return "AvResult [timeStamp=" + timeStamp + ", avId=" + avId + ", speed=" + speed + ", ictInfrastructure="
				+ ictInfrastructure + ", roadConditions=" + roadConditions + ", security=" + security + ", utilitarism="
				+ utilitarism + ", legitimacy=" + legitimacy + ", socialResponsability=" + socialResponsability
				+ ", detection=" + detection + ", identification=" + identification + ", riskAnalysis=" + riskAnalysis
				+ ", reaction=" + reaction + ", execution=" + execution + "]";
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

	public String getIctInfrastructure() {
		return ictInfrastructure;
	}

	public void setIctInfrastructure(String ictInfrastructure) {
		this.ictInfrastructure = ictInfrastructure;
	}

	public String getRoadConditions() {
		return roadConditions;
	}

	public void setRoadConditions(String roadConditions) {
		this.roadConditions = roadConditions;
	}

	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public String getUtilitarism() {
		return utilitarism;
	}

	public void setUtilitarism(String utilitarism) {
		this.utilitarism = utilitarism;
	}

	public String getLegitimacy() {
		return legitimacy;
	}

	public void setLegitimacy(String legitimacy) {
		this.legitimacy = legitimacy;
	}

	public String getSocialResponsability() {
		return socialResponsability;
	}

	public void setSocialResponsability(String socialResponsability) {
		this.socialResponsability = socialResponsability;
	}

	public String getDetection() {
		return detection;
	}

	public void setDetection(String detection) {
		this.detection = detection;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getRiskAnalysis() {
		return riskAnalysis;
	}

	public void setRiskAnalysis(String riskAnalysis) {
		this.riskAnalysis = riskAnalysis;
	}

	public String getReaction() {
		return reaction;
	}

	public void setReaction(String reaction) {
		this.reaction = reaction;
	}

	public String getExecution() {
		return execution;
	}

	public void setExecution(String execution) {
		this.execution = execution;
	}

	

	

}

