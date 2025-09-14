package com.tenant.tenant_analytical_data.controller;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tenant.tenant_analytical_data.dto.TrackEventRequest;
import com.tenant.tenant_analytical_data.service.AnalyticsDataServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
@RestController
@RequestMapping("api/v1/tenant_analytics")
@Tag(
		name="CRUD REST API for Tenant Aanalytical Data ",
		description = "Only INSERT QUERY Transactions and actuator health "
		)
public class AnalyticsDataController {
	private  AnalyticsDataServices analyticsDataServices;
	private static final Logger LOGGER = LoggerFactory.getLogger(AnalyticsDataController.class);
	@Autowired
	public AnalyticsDataController(AnalyticsDataServices analyticsDataServices) {
		super();
		this.analyticsDataServices = analyticsDataServices;
	}
	// save analytical details for tenant
		// http://localhost:8087/api/v1/tenant_analytics/track
		@Operation(
				
				summary = "Create Tenant Analytical Data REST API",
				description = " Create Tenant Analytical data REST API is used to save analytical data " 
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
