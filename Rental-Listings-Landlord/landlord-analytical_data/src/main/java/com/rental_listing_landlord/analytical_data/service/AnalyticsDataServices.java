package com.rental_listing_landlord.analytical_data.service;

import com.rental_listing_landlord.analytical_data.dto.TrackEventRequest;
import com.rental_listing_landlord.analytical_data.entity.AnalyticsData;

import jakarta.servlet.http.HttpServletRequest;

public interface AnalyticsDataServices {
	public void logEvent(TrackEventRequest req, HttpServletRequest http);
}
