package com.fab.rate.fxratedb.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fab.rate.fxratedb.service.model.FXRates;


public interface FXRatesRepository extends JpaRepository<FXRates, Integer>{	

}
