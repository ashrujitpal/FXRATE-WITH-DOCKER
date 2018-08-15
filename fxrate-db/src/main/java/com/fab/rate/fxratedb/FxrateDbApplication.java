package com.fab.rate.fxratedb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class FxrateDbApplication {

	
	public static void main(String[] args) {

		SpringApplication.run(FxrateDbApplication.class, args);
	}
}
