package com.rental_listing_landlord.landlord_login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching
@EnableDiscoveryClient
public class LandlordLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(LandlordLoginApplication.class, args);
	}

}
