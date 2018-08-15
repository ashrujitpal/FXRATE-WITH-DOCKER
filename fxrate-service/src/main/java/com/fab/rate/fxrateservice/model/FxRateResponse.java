package com.fab.rate.fxrateservice.model;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

public class FxRateResponse {

	private String result;
	private BigInteger timestamp;
	private String from;
	private Map<String,String> rates;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public BigInteger getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(BigInteger timestamp) {
		this.timestamp = timestamp;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public Map getRates() {
		return rates;
	}
	public void setRates(Map rates) {
		this.rates = rates;
	}
	
	
	
}
