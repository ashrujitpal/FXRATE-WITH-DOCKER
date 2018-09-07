package com.fab.rate.fxratedb.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fab.rate.fxratedb.service.model.BaseCurrency;
import com.fab.rate.fxratedb.service.model.FXRates;
import java.lang.String;


public interface FXRatesRepository extends JpaRepository<FXRates, Integer>{	
	
    List<FXRates> findByBaseCurrency(String basecurrency);

}
