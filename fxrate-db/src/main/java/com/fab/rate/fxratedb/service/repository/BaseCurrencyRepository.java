package com.fab.rate.fxratedb.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fab.rate.fxratedb.service.model.BaseCurrency;

import java.util.List;


public interface BaseCurrencyRepository extends JpaRepository<BaseCurrency, Integer>{

    List<BaseCurrency> findBaseCurrenciesByCountry(String currency);

}
