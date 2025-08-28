package com.rental_listing_landlord.analytical_data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rental_listing_landlord.analytical_data.dto.TrackEventRequest;
import com.rental_listing_landlord.analytical_data.service.AnalyticsDataServices;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
@RestController
@RequestMapping("api/v1")
public class AnalyticsDataController {
	private  AnalyticsDataServices analyticsDataServices;
	@Autowired
	public AnalyticsDataController(AnalyticsDataServices analyticsDataServices) {
		super();
		this.analyticsDataServices = analyticsDataServices;
	}
	@PostMapping("/track")
	public ResponseEntity<?> track(@Valid @RequestBody TrackEventRequest req, HttpServletRequest http) {
		analyticsDataServices.logEvent(req, http);
	return ResponseEntity.accepted().body("queued");
	}
	@GetMapping("/health")
	public String health() { 
		return "OK"; 
		}
}
