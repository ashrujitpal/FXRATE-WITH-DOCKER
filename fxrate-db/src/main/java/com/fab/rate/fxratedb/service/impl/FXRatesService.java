package com.fab.rate.fxratedb.service.impl;

import java.util.List;

import com.fab.rate.fxratedb.service.model.FXRates;

public interface FXRatesService {
	
	public void addFXRates(FXRates FXRates);
	
	public void deleteFXRates(FXRates FXRates);
	
	public List<FXRates> getAllFXRates();
	

}
