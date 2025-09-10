package com.rental_listing.tenant_config_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@EnableCaching
public class TenantConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TenantConfigServerApplication.class, args);
	}

}
