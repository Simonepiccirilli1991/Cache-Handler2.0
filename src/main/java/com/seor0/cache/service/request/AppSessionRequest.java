package com.seor0.cache.service.request;

public class AppSessionRequest {

	private String bt;
	private String secSession;
	private String appName;
	private Boolean verificato;
	
	public String getBt() {
		return bt;
	}
	public void setBt(String bt) {
		this.bt = bt;
	}
	public String getSecSession() {
		return secSession;
	}
	public void setSecSession(String secSession) {
		this.secSession = secSession;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public Boolean getVerificato() {
		return verificato;
	}
	public void setVerificato(Boolean verificato) {
		this.verificato = verificato;
	}
	
	
}
