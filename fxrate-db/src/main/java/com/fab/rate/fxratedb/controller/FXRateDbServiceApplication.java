package com.fab.rate.fxratedb.controller;

import java.util.List;

import com.fab.rate.fxratedb.service.repository.BaseCurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fab.rate.fxratedb.dao.FxRatesRequest;
import com.fab.rate.fxratedb.service.impl.BaseCurrencyService;
import com.fab.rate.fxratedb.service.impl.FXRatesService;
import com.fab.rate.fxratedb.service.model.BaseCurrency;
import com.fab.rate.fxratedb.service.model.FXRates;


@RestController
@RequestMapping("/fxratedb")
public class FXRateDbServiceApplication {
	
	private BaseCurrencyService baseCurrencyService;
	
	private FXRatesService fxRateService;

	@Autowired
	private BaseCurrencyRepository baseCurrencyRepository;
	
	@Autowired
    public void setBaseCurrencyService(BaseCurrencyService baseCurrencyService) {
        this.baseCurrencyService = baseCurrencyService;
    }
    
    @Autowired
    public void setFXRatesService(FXRatesService fxRateService) {
        this.fxRateService = fxRateService;
    }
	
	@PostMapping("add/{basecurrency}/{country}")
	public String addBaseCurrency(
			@PathVariable("basecurrency") final String basecurrency,
			@PathVariable("country") final String country){
		
			BaseCurrency currency = new BaseCurrency();
			currency.setCountry(country);
			currency.setCurrency(basecurrency);
			
			baseCurrencyService.addBaseCurrency(currency);
		
		return "Success";
		
	}
	
	@PostMapping("/add/fxrates")
	public boolean addRates(@RequestBody FxRatesRequest request) {
		
		FXRates rates = new FXRates();
		
		rates.setBaseCurrency(request.getFrom());
		rates.setTimestamp(request.getTimestamp());
		rates.setRate(request.getRates().toString());
		
		fxRateService.addFXRates(rates);
		
		return true;
	}
	
	@GetMapping("/all")
	public List<BaseCurrency> getAllBaseCurrencies(){
		return baseCurrencyService.getAllBaseCurrencies();
		
	}

	@GetMapping("/country/{country}")
	public String getBaseCurrency(@PathVariable("country") String country) {

		List<BaseCurrency> baseCurrencies = baseCurrencyRepository.findBaseCurrenciesByCountry(country);

		return baseCurrencies.iterator().next().getCurrency();

	}

	@GetMapping("/fxrates")
	public List<FXRates> getAllFXRates(){
		
		return fxRateService.getAllFXRates();
		
	}
}