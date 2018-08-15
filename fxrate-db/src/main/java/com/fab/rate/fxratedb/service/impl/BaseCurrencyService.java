package com.fab.rate.fxratedb.service.impl;

import java.util.List;

import com.fab.rate.fxratedb.service.model.BaseCurrency;

public interface BaseCurrencyService {
	
	public void addBaseCurrency(BaseCurrency baseCurrency);
	
	public void deleteBaseCurrency(BaseCurrency baseCurrency);
	
	public List<BaseCurrency> getAllBaseCurrencies();
	

}
