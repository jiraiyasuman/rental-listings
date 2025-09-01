package com.rental_listing_landlord.landlord_login.service;

import com.rental_listing_landlord.landlord_login.entity.OtpToken;
import com.rental_listing_landlord.landlord_login.entity.User;

public interface OtpService {


	public OtpToken createAndSendOtp(User user);
	
	public boolean verifyOtp(User user,String code);
	
	public boolean canResend(User user);
	
	public OtpToken resendOtp(User user);
	
}
