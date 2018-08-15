package com.fab.rate.fxrateservice.controller;


import com.fab.rate.fxrateservice.model.FXRate;
import com.fab.rate.fxrateservice.model.FxRateResponse;

import groovy.util.logging.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;


@Slf4j
@PropertySource("classpath:custom.properties")
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
    public FxRateResponse getFXRateByCountry(@PathVariable("country") String country){

        String url = fxrateDB_URL + "/fxratedb/country/"+country;
        
        System.out.println("fxrateDB_URL ::::::::: "+ url);
        
        String currency;
        
        FxRateResponse fxRateResponse = null;
        
        currency = restTemplate.getForObject(url, String.class, country);
        
        ResponseEntity<FxRateResponse> response = null ;
        
		try {
			response =
					restTemplate.exchange(new URI(exchangeURL+"/"+currency.replaceAll("'", "")), 
					HttpMethod.GET, null, FxRateResponse.class);
			
			 fxRateResponse = response.getBody();
		
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    	
		try {
			
			restTemplate.exchange(new URI(fxrateDB_URL + "/fxratedb/add/fxrates"), 
					HttpMethod.POST, new HttpEntity<>(fxRateResponse), Boolean.class);
			
		} catch (RestClientException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fxRateResponse;
    	
    }

    @GetMapping("/country/all")
    public FXRate getFXRateByCountry(){
    	
    	return new FXRate();

    }
}
