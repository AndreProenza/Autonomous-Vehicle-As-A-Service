package avaas.kafka.model;

public class AvResult {
	
	private String timeStamp;
	private String avId;
	private String speed;
	private String ictInfrastructure;
	private String roadConditions;
	private String security;
	private String utilitarianism;
	private String legitimacy;
	private String socialResponsibility;
	private String detection;
	private String identification;
	private String riskAnalysis;
	private String reaction;
	private String execution;
	
	public AvResult() {
		//Does nothing
	}

	public AvResult(String timeStamp, String avId, String speed, String ictInfrastructure, String roadConditions,
			String security, String utilitarianism, String legitimacy, String socialResponsibility, String detection,
			String identification, String riskAnalysis, String reaction, String execution) {
		super();
		this.timeStamp = timeStamp;
		this.avId = avId;
		this.speed = speed;
		this.ictInfrastructure = ictInfrastructure;
		this.roadConditions = roadConditions;
		this.security = security;
		this.utilitarianism = utilitarianism;
		this.legitimacy = legitimacy;
		this.socialResponsibility = socialResponsibility;
		this.detection = detection;
		this.identification = identification;
		this.riskAnalysis = riskAnalysis;
		this.reaction = reaction;
		this.execution = execution;
	}
	
	

	@Override
	public String toString() {
		return "AvResult [timeStamp=" + timeStamp + ", avId=" + avId + ", speed=" + speed + ", ictInfrastructure="
				+ ictInfrastructure + ", roadConditions=" + roadConditions + ", security=" + security
				+ ", utilitarianism=" + utilitarianism + ", legitimacy=" + legitimacy + ", socialResponsibility="
				+ socialResponsibility + ", detection=" + detection + ", identification=" + identification
				+ ", riskAnalysis=" + riskAnalysis + ", reaction=" + reaction + ", execution=" + execution + "]";
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

	public String getUtilitarianism() {
		return utilitarianism;
	}

	public void setUtilitarianism(String utilitarianism) {
		this.utilitarianism = utilitarianism;
	}

	public String getLegitimacy() {
		return legitimacy;
	}

	public void setLegitimacy(String legitimacy) {
		this.legitimacy = legitimacy;
	}

	public String getSocialResponsibility() {
		return socialResponsibility;
	}

	public void setSocialResponsibility(String socialResponsibility) {
		this.socialResponsibility = socialResponsibility;
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
