package com.rental_listing_landlord.analytical_data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@EnableAsync
public class AnalyticalDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnalyticalDataApplication.class, args);
	}

}
