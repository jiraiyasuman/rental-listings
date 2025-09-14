package com.rental_listing_landlord.landlord_config_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
@ComponentScan(basePackages = "com.rental_listing_landlord.landlord_config_server")
@OpenAPIDefinition(
		info=@Info(
				title = "Configuration Server for LANDLORD REST API Documentation",
				description = "SpringBoot Configuration Server for LANDLORD REST API Documentation",
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
						description = "SpringBoot Configuration Server for LANDLORD REST API Management Documentation",
						url=""
						)
		)
public class LandlordConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LandlordConfigServerApplication.class, args);
	}

}
