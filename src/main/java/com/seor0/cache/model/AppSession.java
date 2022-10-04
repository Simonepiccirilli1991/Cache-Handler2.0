package com.seor0.cache.model;

import java.io.Serializable;

public class AppSession implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String bt;
	private String secSessionId;
	private String appSessionId;
	private boolean sessionActive;
	private String geneTime;
	private String updateTime;
	private String appName;
	
	public String getBt() {
		return bt;
	}
	public void setBt(String bt) {
		this.bt = bt;
	}
	public String getSecSessionId() {
		return secSessionId;
	}
	public void setSecSessionId(String secSessionId) {
		this.secSessionId = secSessionId;
	}
	public String getAppSessionId() {
		return appSessionId;
	}
	public void setAppSessionId(String appSessionId) {
		this.appSessionId = appSessionId;
	}
	public boolean isSessionActive() {
		return sessionActive;
	}
	public void setSessionActive(boolean sessionActive) {
		this.sessionActive = sessionActive;
	}
	public String getGeneTime() {
		return geneTime;
	}
	public void setGeneTime(String geneTime) {
		this.geneTime = geneTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	
	
	
}
