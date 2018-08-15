package com.fab.rate.fxratedb.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fab.rate.fxratedb.service.model.BaseCurrency;
import com.fab.rate.fxratedb.service.repository.BaseCurrencyRepository;


@Service
public class BaseCurrencyServiceImpl implements BaseCurrencyService {

	private BaseCurrencyRepository baseCurrencyRepo;
	
	@Autowired
    public void setBaseCurrencyRepository(BaseCurrencyRepository baseCurrencyRepo) {
        this.baseCurrencyRepo = baseCurrencyRepo;
    }
	
	@Override
	public void addBaseCurrency(BaseCurrency baseCurrency) {
		
		// TODO Auto-generated method stub
		baseCurrencyRepo.save(baseCurrency);
		
	}

	@Override
	public void deleteBaseCurrency(BaseCurrency baseCurrency) {
		
		// TODO Auto-generated method stub
		baseCurrencyRepo.delete(baseCurrency);
	}
	

	@Override
	public List<BaseCurrency> getAllBaseCurrencies() {
		
		// TODO Auto-generated method stub
		return  baseCurrencyRepo.findAll();
	}	

}
