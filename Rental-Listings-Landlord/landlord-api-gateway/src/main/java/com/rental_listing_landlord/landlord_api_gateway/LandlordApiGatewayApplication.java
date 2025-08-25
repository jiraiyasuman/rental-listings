package com.rental_listing_landlord.landlord_api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class LandlordApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(LandlordApiGatewayApplication.class, args);
	}

}
