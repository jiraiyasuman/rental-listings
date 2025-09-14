package com.rental_listing_landlord.analytical_data.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rental_listing_landlord.analytical_data.dto.TrackEventRequest;
import com.rental_listing_landlord.analytical_data.service.AnalyticsDataServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
@RestController

@RequestMapping("api/v1/landlord_analytics")
@Tag(
		name="CRUD REST API for Landlord Aanalytical Data ",
		description = "Only INSERT QUERY Transactions and actuator health "
		)
public class AnalyticsDataController {
	private  AnalyticsDataServices analyticsDataServices;
	@Autowired
	public AnalyticsDataController(AnalyticsDataServices analyticsDataServices) {
		super();
		this.analyticsDataServices = analyticsDataServices;
	}
	private static final Logger LOGGER = LoggerFactory.getLogger(AnalyticsDataController.class);
	// save analytical details for landlords
	// http://localhost:8082/api/v1/landlord_analytics/track
	@Operation(
			
			summary = "Create Landlord Analytical Data REST API",
			description = " Create Landlord Analytical data REST API is used to save analytical data " 
			)
	@ApiResponse(
			responseCode = "201",
			description = "HTTP STATUS 201 created"
			)
	@PostMapping("/track")
	public ResponseEntity<?> track(@Valid @RequestBody TrackEventRequest req, HttpServletRequest http) {
		analyticsDataServices.logEvent(req, http);
		LOGGER.info("Analytics Data Post Controller is being executed successfully");
	return ResponseEntity.accepted().body("queued");
	}
	@GetMapping("/health")
	public String health() { 
		return "OK"; 
		}
}
