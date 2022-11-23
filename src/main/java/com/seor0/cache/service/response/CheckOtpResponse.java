package com.seor0.cache.service.response;

public class CheckOtpResponse {

	private String codiceEsito;
	private Boolean checkOk;
	private Boolean mail = true;
	
	public String getCodiceEsito() {
		return codiceEsito;
	}
	public void setCodiceEsito(String codiceEsito) {
		this.codiceEsito = codiceEsito;
	}
	public Boolean getCheckOk() {
		return checkOk;
	}
	public void setCheckOk(Boolean checkOk) {
		this.checkOk = checkOk;
	}
	
	
}
