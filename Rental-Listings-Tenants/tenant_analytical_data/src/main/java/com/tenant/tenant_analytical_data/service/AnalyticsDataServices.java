package com.tenant.tenant_analytical_data.service;


import com.tenant.tenant_analytical_data.dto.TrackEventRequest;

import jakarta.servlet.http.HttpServletRequest;

public interface AnalyticsDataServices {
	public void logEvent(TrackEventRequest req, HttpServletRequest http);
}
