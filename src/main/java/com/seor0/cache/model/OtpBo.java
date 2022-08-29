package com.seor0.cache.model;

import java.io.Serializable;

public class OtpBo implements Serializable{

//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private static final long serialVersionUID = 1L;
	private String trxId;
	private String otp;
	private String username;
	private String profilo;
	private String generateTime;
	
	
	public String getTrxId() {
		return trxId;
	}
	public void setTrxId(String trxId) {
		this.trxId = trxId;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getProfilo() {
		return profilo;
	}
	public void setProfilo(String profilo) {
		this.profilo = profilo;
	}
	public String getGenerateTime() {
		return generateTime;
	}
	public void setGenerateTime(String generateTime) {
		this.generateTime = generateTime;
	}
	
	
	
	
	
	
}
