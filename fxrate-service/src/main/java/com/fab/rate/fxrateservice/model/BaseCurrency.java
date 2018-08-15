package com.fab.rate.fxrateservice.model;


public class BaseCurrency {

	private Integer id;
	
	private String country;
	
	private String currency;
	
	public BaseCurrency() {

		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
}
