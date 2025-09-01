package com.rental_listing_landlord.landlord_login.service;

public interface EmailService {

	public void sendOtpEmailAsync(String to,String subject, String message);
}
