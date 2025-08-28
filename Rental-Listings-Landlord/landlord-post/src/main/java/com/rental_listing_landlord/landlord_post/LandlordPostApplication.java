package com.rental_listing_landlord.landlord_post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
@EnableCaching
public class LandlordPostApplication {

	public static void main(String[] args) {
		SpringApplication.run(LandlordPostApplication.class, args);
	}

}
