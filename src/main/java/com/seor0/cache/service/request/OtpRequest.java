package com.seor0.cache.service.request;

import org.springframework.lang.NonNull;

public class OtpRequest {

	@NonNull 
	private String username;
	@NonNull 
	private String otp;
	@NonNull 
	private String profilo;
	
	private String trxId;
	
	public OtpRequest(String username, String otp, String profilo, String trxId) {
		super();
		this.username = username;
		this.otp = otp;
		this.profilo = profilo;
		this.trxId = trxId;
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
	
	public String getTrxId() {
		return trxId;
	}
	
	
}
