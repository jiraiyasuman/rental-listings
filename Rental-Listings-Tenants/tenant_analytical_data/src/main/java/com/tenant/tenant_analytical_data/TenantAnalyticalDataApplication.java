package com.tenant.tenant_analytical_data;

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
@EnableDiscoveryClient
@EnableCaching
@EnableFeignClients
@ComponentScan(basePackages = "com.tenant.tenant_analytical_data")
@OpenAPIDefinition(
		
		info =@Info(
				title="Spring Boot TENANT Analaytical Data API Documentation",
				description = "Spring Boot TENANT Analaytical Data REST API Documentation",
				version = "version 1.0",
				contact=@Contact(
						name="Suman Talukdar",
						email="suman.talukdar53@gamil.com",
						url="https://jiraiyasuman.github.io/portfolio/"
						),
				license = @License(
						name = " Apache 2.0 ",
						url=""
						)
				
				),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot TENANT Analytical Data Documentation",
				url="https://jiraiyasuman.github.io/portfolio/"
				)
		)
public class TenantAnalyticalDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(TenantAnalyticalDataApplication.class, args);
	}

}
