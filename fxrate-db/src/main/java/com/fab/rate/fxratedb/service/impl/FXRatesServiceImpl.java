package com.fab.rate.fxratedb.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fab.rate.fxratedb.service.model.FXRates;
import com.fab.rate.fxratedb.service.repository.FXRatesRepository;


@Service
public class FXRatesServiceImpl implements FXRatesService {

	private FXRatesRepository fxRatesRepo;
	
	@Autowired
    public void setFXRatesRepository(FXRatesRepository fxRatesRepo) {
        this.fxRatesRepo = fxRatesRepo;
    }
	
	@Override
	public void addFXRates(FXRates FXRates) {
		
		// TODO Auto-generated method stub
		fxRatesRepo.save(FXRates);
		
	}

	@Override
	public void deleteFXRates(FXRates FXRates) {
		
		// TODO Auto-generated method stub
		fxRatesRepo.delete(FXRates);
	}
	

	@Override
	public List<FXRates> getAllFXRates() {
		
		// TODO Auto-generated method stub
		return  fxRatesRepo.findAll();
	}	

}
