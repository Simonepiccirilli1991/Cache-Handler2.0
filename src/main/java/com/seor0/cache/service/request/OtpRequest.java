package com.seor0.cache.service.request;

import org.springframework.lang.NonNull;

public class OtpRequest {

	@NonNull 
	private String username;
	@NonNull 
	private String otp;
	@NonNull 
	private String profilo;
	
	public OtpRequest(String username, String otp, String profilo) {
		super();
		this.username = username;
		this.otp = otp;
		this.profilo = profilo;
	}

	public String getUsername() {
		return username;
	}

	public String getOtp() {
		return otp;
	}
	public String getProfilo() {
		return profilo;
	}
	
	
	
	
}
