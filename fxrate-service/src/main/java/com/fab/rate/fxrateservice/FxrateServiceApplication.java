package com.fab.rate.fxrateservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;


@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
@EnableHystrix
public class FxrateServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(FxrateServiceApplication.class, args);
	}
		
}
