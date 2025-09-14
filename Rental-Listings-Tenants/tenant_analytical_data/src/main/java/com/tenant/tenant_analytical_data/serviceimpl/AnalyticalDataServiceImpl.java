package com.tenant.tenant_analytical_data.serviceimpl;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenant.tenant_analytical_data.dto.TrackEventRequest;
import com.tenant.tenant_analytical_data.entity.AnalyticsData;
import com.tenant.tenant_analytical_data.repository.AnalyticsDataRepository;
import com.tenant.tenant_analytical_data.service.AnalyticsDataServices;

import io.github.resilience4j.retry.annotation.Retry;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class AnalyticalDataServiceImpl implements AnalyticsDataServices{

	private static final Logger LOGGER = LoggerFactory.getLogger(AnalyticalDataServiceImpl.class);
	private AnalyticsDataRepository analyticsDataRepository;
	@Autowired
	public AnalyticalDataServiceImpl(AnalyticsDataRepository analyticsDataRepository) {
		super();
		this.analyticsDataRepository = analyticsDataRepository;
	}
	@Override
	@Transactional
	@Retry(
			name="${spring.application.name}",
			fallbackMethod = ""
			)
	public void logEvent(TrackEventRequest req, HttpServletRequest http) {
		AnalyticsData e = new AnalyticsData();
		e.setSessionId(req.getSessionId());
		e.setUserId(req.getUserId());
		e.setEventType(req.getEventType());
		e.setPageUrl(req.getPageUrl());
		e.setReferrer(req.getReferrer());
		e.setBrowser(req.getBrowser());
		e.setOs(req.getOs());
		e.setMetaData(req.getMetadata());
		String ip = http.getHeader("X-Forwarded-For");
		if (ip == null || ip.isBlank()) ip = http.getRemoteAddr();
		e.setIpAddress(ip);
		e.setUserAgent(http.getHeader("User-Agent"));
		LOGGER.info("Save tenant analytical data is being executed successfully");
		analyticsDataRepository.save(e);
	}

	
	
}
