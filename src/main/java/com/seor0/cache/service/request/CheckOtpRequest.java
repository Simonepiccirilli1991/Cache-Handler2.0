package com.seor0.cache.service.request;

import org.springframework.lang.NonNull;

public class CheckOtpRequest {
	
	@NonNull
	private String otp;
	@NonNull
	private String trxId;
	@NonNull
	private String profilo;
	
	public String getOtp() {
		return otp;
	}
	public String getTrxId() {
		return trxId;
	}
	public String getProfilo() {
		return profilo;
	}
	public CheckOtpRequest(String otp, String trxId, String profilo) {
		super();
		this.otp = otp;
		this.trxId = trxId;
		this.profilo = profilo;
	}

	
	
}
