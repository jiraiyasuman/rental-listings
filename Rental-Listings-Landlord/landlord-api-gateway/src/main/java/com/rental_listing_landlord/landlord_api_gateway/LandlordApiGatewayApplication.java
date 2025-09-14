package com.rental_listing_landlord.landlord_api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = "com.rental_listing_landlord.landlord_api_gateway")
@OpenAPIDefinition(
		info=@Info(
				title = "API GATEWAY for LANDLORD REST API Documentation",
				description = "SpringBoot API GATEWAY for LANDLORD REST API Documentation",
				version = "version 1.0",
				contact = @Contact(
						name="Suman Talukdar",
						email="suman.talukdar53@gmail.com",
						url="https://jiraiyasuman.github.io/portfolio/"
						),license = @License(
								name="Apache 2.0",
								url="https://jiraiyasuman.github.io/portfolio/"
								)
				),externalDocs = @ExternalDocumentation(
						description = "SpringBoot API GATEWAY for LANDLORD REST API Management Documentation",
						url=""
						)
		)
public class LandlordApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(LandlordApiGatewayApplication.class, args);
	}

}
