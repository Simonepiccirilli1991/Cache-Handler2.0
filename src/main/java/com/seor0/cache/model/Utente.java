package com.seor0.cache.model;



//@Entity
//@Table(schema="cacheHaze", name = "utente")
public class Utente {

//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String bt;
	private String abi;

	private String username;

	private String email;
	private String cellulare;
	private Boolean cellCertificato;
	private Boolean mailCeltificato;
	private Boolean enforced;
	
	public String getBt() {
		return bt;
	}
	public void setBt(String bt) {
		this.bt = bt;
	}
	public String getAbi() {
		return abi;
	}
	public void setAbi(String abi) {
		this.abi = abi;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCellulare() {
		return cellulare;
	}
	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}
	public Boolean getCellCertificato() {
		return cellCertificato;
	}
	public void setCellCertificato(Boolean cellCertificato) {
		this.cellCertificato = cellCertificato;
	}
	public Boolean getMailCeltificato() {
		return mailCeltificato;
	}
	public void setMailCeltificato(Boolean mailCeltificato) {
		this.mailCeltificato = mailCeltificato;
	}
	public Boolean getEnforced() {
		return enforced;
	}
	public void setEnforced(Boolean enforced) {
		this.enforced = enforced;
	}
	
	
}
