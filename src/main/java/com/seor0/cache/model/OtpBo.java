package com.seor0.cache.model;



import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



public class OtpBo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long trxId;
	private String otp;
	private String username;
	private String profilo;
	private String generateTime;
	
	public long getTrxId() {
		return trxId;
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
	@Override
	public String toString() {
		return "" +getTrxId() + "";
	}
	
	
	
	
	
}
