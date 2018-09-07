package com.fab.rate.fxrateservice.controller;


import com.fab.rate.fxrateservice.model.FXRates;
import com.fab.rate.fxrateservice.model.FxRateResponse;
import com.fab.rate.fxrateservice.model.FxRatesRequest;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



@RefreshScope
@RestController
@RequestMapping("/fxrateservice")
public class FXRateServiceCotroller {

    @Autowired
    RestTemplate restTemplate;
    
    @Value("${exchange.url}")
    String exchangeURL;
    
	@Value("${api.url.fxratedb}")
	private String fxrateDB_URL;
    
    
    @GetMapping("/country/{country}")
    @HystrixCommand(fallbackMethod = "getDefaultFxRateByCountry")
    public  FxRateResponse getFXRateByCountry(@PathVariable("country") String country){
    //public  FxRateResponse getFXRateByCountry(@PathVariable("country") String country){

        String url = fxrateDB_URL + "/fxratedb/country/"+country;
        
        System.out.println("fxrateDB_URL ::::::::: "+ url);
        System.out.println("exchangeURL ::::::::: "+ exchangeURL);
        
        String currency;
        
        FxRateResponse fxRateResponse = null;
        
        currency = restTemplate.getForObject(url, String.class, country);
        
        ResponseEntity<FxRateResponse> response = null ;        
        
		
			try {
				response =
						restTemplate.exchange(new URI(exchangeURL+"/"+currency.replaceAll("'", "")), 
						HttpMethod.GET, null, FxRateResponse.class);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				throw new RuntimeException();
			} 
			
			if(response.getStatusCode() == HttpStatus.OK){
				fxRateResponse = response.getBody();
				
				if (null != fxRateResponse.getFrom() && !fxRateResponse.getResult().equals("error")) {
					
					FxRatesRequest fxRatesRequest = new FxRatesRequest();
					
					fxRatesRequest.setFrom(fxRateResponse.getFrom());
					fxRatesRequest.setRates(fxRateResponse.getRates());
					fxRatesRequest.setResult(fxRateResponse.getResult());
					fxRatesRequest.setTimestamp(fxRateResponse.getTimestamp());
					
					HttpEntity<FxRatesRequest> request = new HttpEntity<FxRatesRequest>(fxRatesRequest);				
					
					try {
						restTemplate.exchange(new URI(fxrateDB_URL + "/fxratedb/add/fxrates"), 
								HttpMethod.POST, request, Boolean.class);
					} catch (Exception e) {
						
						//throw new RuntimeException();
					}					
				}
			}
			
		return fxRateResponse;
    	
    }

    @GetMapping("/country/all")
    public FXRates getFXRateByCountry(){
    	
    	return new FXRates();

    }
    
    @SuppressWarnings("unused")
    FxRateResponse getDefaultFxRateByCountry(@PathVariable("country") String country){

    	ResponseEntity<List> response = null ;
        FxRateResponse fxRateResponse = new FxRateResponse(); 
		
        String url = fxrateDB_URL + "/fxratedb/country/"+country;
        
        String currency;
        
        currency = restTemplate.getForObject(url, String.class, country);
        
        url = fxrateDB_URL + "/fxratedb/fxrates/"+currency.replaceAll("'", "");
        System.out.println("fxrateDB_URL getDefaultFxRateByCountry ::::::::: "+ url);
        
		try {
			response = restTemplate.exchange(new URI(url),HttpMethod.GET, null, List.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			//throw new RuntimeException();
			
		} 
		
		List<LinkedHashMap> fxRateList = (ArrayList<LinkedHashMap>)response.getBody();
		LinkedHashMap fxRatesMap = (LinkedHashMap) fxRateList.iterator().next();
		
		fxRateResponse.setFrom((String)fxRatesMap.get("baseCurrency"));
		fxRateResponse.setResult("success");
		fxRateResponse.setTimestamp((BigInteger)fxRatesMap.get("timetamp"));
		
		Map<String, String> ratesMap = new HashMap<String, String>();
		
		String[] ratePairs = ((String)fxRatesMap.get("rate")).split(",");
		
		for (int i = 0; i < ratePairs.length; i++) {
			String[] pair =  ratePairs[i].split("=");
			ratesMap.put(pair[0], pair[1]);
			
		}
		
		fxRateResponse.setRates(ratesMap);
    	
        return fxRateResponse;
    }
}
