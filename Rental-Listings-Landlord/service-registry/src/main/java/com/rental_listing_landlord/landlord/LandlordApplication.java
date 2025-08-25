package com.rental_listing_landlord.landlord;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class LandlordApplication {

	public static void main(String[] args) {
		SpringApplication.run(LandlordApplication.class, args);
	}

}
