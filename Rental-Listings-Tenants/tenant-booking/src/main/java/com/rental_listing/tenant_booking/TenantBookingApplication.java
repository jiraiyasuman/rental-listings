package com.rental_listing.tenant_booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableCaching
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = "com.rental_listing.tenant_booking")
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot Employee REST API Documentation",
				description = "Spring Boot Employee REST API Documentation",
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
				description = "Spring Boot Emloyee Management Documentation",
				url = ""
		)
		)
public class TenantBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TenantBookingApplication.class, args);
	}

}
