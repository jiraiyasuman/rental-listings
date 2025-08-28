package com.rental_listing_landlord.analytical_data.dto;

import jakarta.validation.constraints.NotBlank;

public class TrackEventRequest {
	@NotBlank
	private String sessionId;
	private String userId;
	@NotBlank
	private String eventType; // e.g., page_view
	@NotBlank
	private String pageUrl;
	private String referrer;
	private String browser;
	private String os;
	private String metadata; // JSON string
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public String getReferrer() {
		return referrer;
	}
	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public TrackEventRequest(@NotBlank String sessionId, String userId, @NotBlank String eventType,
			@NotBlank String pageUrl, String referrer, String browser, String os, String metadata) {
		super();
		this.sessionId = sessionId;
		this.userId = userId;
		this.eventType = eventType;
		this.pageUrl = pageUrl;
		this.referrer = referrer;
		this.browser = browser;
		this.os = os;
		this.metadata = metadata;
	}
	public TrackEventRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TrackEventRequest [sessionId=" + sessionId + ", userId=" + userId + ", eventType=" + eventType
				+ ", pageUrl=" + pageUrl + ", referrer=" + referrer + ", browser=" + browser + ", os=" + os
				+ ", metadata=" + metadata + "]";
	}
	
}
