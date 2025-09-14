package com.rental_listing_landlord.landlord_post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
@EnableDiscoveryClient
@SpringBootApplication
@EnableCaching
@ComponentScan(basePackages = "com.rental_listing_landlord.landlord_post")
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot LANDLORD POST REST API Documentation",
				description = "Spring Boot LANDLORD POST REST API Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Suman Talukdar",
						email = "suman.talukdar53@gmail.com",
						url = "https://jiraiyasuman.github.io/portfolio/"
				),
				license = @License(
						name = "Apache 2.0",
						url = ""
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot LANDLORD POST Management Documentation",
				url = ""
		)
		)
public class LandlordPostApplication {

	public static void main(String[] args) {
		SpringApplication.run(LandlordPostApplication.class, args);
	}

}
