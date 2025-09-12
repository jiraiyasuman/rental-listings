package com.online_rental.tenant_login.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
@Entity(name="otp_login")
public class OtpLogin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="code")
	private String code;
	@Column(name="expires_at")
	private LocalDateTime expiresAt;
	@Column(name="resend_available")
	private LocalDateTime resendAvailableAt;
	private boolean used = false;
	@ManyToOne(fetch = FetchType.LAZY)
	private Login user;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}
	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}
	public LocalDateTime getResendAvailableAt() {
		return resendAvailableAt;
	}
	public void setResendAvailableAt(LocalDateTime resendAvailableAt) {
		this.resendAvailableAt = resendAvailableAt;
	}
	public boolean isUsed() {
		return used;
	}
	public void setUsed(boolean used) {
		this.used = used;
	}
	public Login getUser() {
		return user;
	}
	public void setUser(Login user) {
		this.user = user;
	}
	public OtpLogin(int id, String code, LocalDateTime expiresAt, LocalDateTime resendAvailableAt, boolean used,
			Login user) {
		super();
		this.id = id;
		this.code = code;
		this.expiresAt = expiresAt;
		this.resendAvailableAt = resendAvailableAt;
		this.used = used;
		this.user = user;
	}
	public OtpLogin() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "OtpLogin [id=" + id + ", code=" + code + ", expiresAt=" + expiresAt + ", resendAvailableAt="
				+ resendAvailableAt + ", used=" + used + ", user=" + user + "]";
	}
	
}
