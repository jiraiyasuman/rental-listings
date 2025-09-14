package com.rental_listing_landlord.landlord;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableEurekaServer
@ComponentScan(basePackages = "com.rental_listing_landlord.landlord")
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot LANDLORD SERVICE REGISTRY REST API Documentation",
				description = "Spring Boot LANDLORD SERVICE REGISTRY REST API Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Suman Talukdar",
						email = "suman.talukdar53@gmail.com",
						url = ""
				),
				license = @License(
						name = "Apache 2.0",
						url = ""
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot LANDLORD SERVICE REGISTRY Management Documentation",
				url = ""
		)
		)
public class LandlordApplication {

	public static void main(String[] args) {
		SpringApplication.run(LandlordApplication.class, args);
	}

}
