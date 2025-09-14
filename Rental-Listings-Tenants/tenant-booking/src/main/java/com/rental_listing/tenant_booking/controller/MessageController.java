package com.rental_listing.tenant_booking.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class MessageController {

	@Value("${spring.boot.message}")
	private String message;
	@GetMapping("/tenant-analytical-data/message")
	public String messages() {
		return message;
	}
}
