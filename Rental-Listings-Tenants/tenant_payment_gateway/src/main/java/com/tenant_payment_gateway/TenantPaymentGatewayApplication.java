package com.tenant_payment_gateway;

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
@EnableCaching
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = "com.tenant_payment_gateway")
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot Tenant Payment REST API Documentation",
				description = "Spring Boot Tenant Payment REST API Documentation",
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
				description = "Spring Boot Tenant Payment Management Documentation",
				url = ""
		)
		)
public class TenantPaymentGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(TenantPaymentGatewayApplication.class, args);
	}

}
