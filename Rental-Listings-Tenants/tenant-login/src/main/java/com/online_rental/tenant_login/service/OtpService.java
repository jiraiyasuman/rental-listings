package com.online_rental.tenant_login.service;

import com.online_rental.tenant_login.entity.Login;
import com.online_rental.tenant_login.entity.OtpLogin;

public interface OtpService {

public OtpLogin createAndSendOtp(Login user);
	
	public boolean verifyOtp(Login user,String code);
	
	public boolean canResend(Login user);
	
	public OtpLogin resendOtp(Login user);
}
