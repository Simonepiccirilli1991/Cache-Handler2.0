package com.seor0.cache.service.request;


import org.springframework.lang.NonNull;



public class OtpRequest {

	@NonNull 
	private String username;
	@NonNull 
	private String profilo;
	@NonNull
	private String email;
	
	private String trxId;
	
	public OtpRequest(String username, String email, String profilo, String trxId) {
		super();
		this.username = username;
		this.email = email;
		this.profilo = profilo;
		this.trxId = trxId;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}
	public String getProfilo() {
		return profilo;
	}
	
	public String getTrxId() {
		return trxId;
	}
	
}
