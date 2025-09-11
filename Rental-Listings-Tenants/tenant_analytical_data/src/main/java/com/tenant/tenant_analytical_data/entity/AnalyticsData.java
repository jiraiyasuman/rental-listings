package com.tenant.tenant_analytical_data.entity;

import java.time.Instant;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="analytics_data")
public class AnalyticsData {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="session_id",nullable=false,length=64)
	private String sessionId;
	@Column(name="user_id")
	private String userId;
	@Column(name="event_type",nullable=false,length=32)
	private String eventType;
	@Column(name="page_url",nullable=false,length=2048)
	private String pageUrl;
	@Column(name="referrer",length=2048)
	private String referrer;
	@Column(name="user_agent",columnDefinition = "TEXT")
	private String userAgent;
	@Column(name="ip_address",length=45)
	private String ipAddress;
	@Column(name="country",length=64)
	private String country;
	@Column(name="browser",length=64)
	private String browser;
	@Column(name="os",length=64)
	private String os;
	@Column(name="meta_data",columnDefinition = "JSON")
	private String metaData;
	@Column(name="created_at",nullable=false)
    private Instant createdAt = Instant.now();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
	public String getMetaData() {
		return metaData;
	}
	public void setMetaData(String metaData) {
		this.metaData = metaData;
	}
	public Instant getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
	public AnalyticsData(Long id, String sessionId, String userId, String eventType, String pageUrl, String referrer,
			String userAgent, String ipAddress, String country, String browser, String os, String metaData,
			Instant createdAt) {
		super();
		this.id = id;
		this.sessionId = sessionId;
		this.userId = userId;
		this.eventType = eventType;
		this.pageUrl = pageUrl;
		this.referrer = referrer;
		this.userAgent = userAgent;
		this.ipAddress = ipAddress;
		this.country = country;
		this.browser = browser;
		this.os = os;
		this.metaData = metaData;
		this.createdAt = createdAt;
	}
	public AnalyticsData() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "AnalyticsData [id=" + id + ", sessionId=" + sessionId + ", userId=" + userId + ", eventType="
				+ eventType + ", pageUrl=" + pageUrl + ", referrer=" + referrer + ", userAgent=" + userAgent
				+ ", ipAddress=" + ipAddress + ", country=" + country + ", browser=" + browser + ", os=" + os
				+ ", metaData=" + metaData + ", createdAt=" + createdAt + "]";
	}
	
}
