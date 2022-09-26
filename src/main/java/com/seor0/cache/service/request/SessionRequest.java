package com.seor0.cache.service.request;

public class SessionRequest {

	private String username;
	private String bt;
	private String scope;
	
	// header valorizzati nel controller // commad 
	
	private String abi;
	private String canale;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBt() {
		return bt;
	}
	public void setBt(String bt) {
		this.bt = bt;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getAbi() {
		return abi;
	}
	public void setAbi(String abi) {
		this.abi = abi;
	}
	public String getCanale() {
		return canale;
	}
	public void setCanale(String canale) {
		this.canale = canale;
	}
	
	
}
