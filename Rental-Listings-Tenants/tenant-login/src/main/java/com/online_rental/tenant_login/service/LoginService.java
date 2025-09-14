package com.online_rental.tenant_login.service;

import com.online_rental.tenant_login.entity.Login;

public interface LoginService {

public Login registerIfNotExists(Login login);
	
	public boolean isLocked(Login user);
	
	public boolean validatePasswordAndUpdateAttempts(Login user, String password);
	
	public Login findByUserName(String email);
}
