package com.online_rental.tenant_login.service;

public interface EmailService {
	public void sendOtpEmailAsync(String to,String subject, String message);
}
