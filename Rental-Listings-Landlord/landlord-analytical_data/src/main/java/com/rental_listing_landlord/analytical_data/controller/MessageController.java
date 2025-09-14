package com.rental_listing_landlord.analytical_data.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class MessageController {

	@Value("${sprig.boot.message}")
	private String message;
	@GetMapping("/landlord-analytical-data/message")
	public String messages() {
		return message;
	}
}
