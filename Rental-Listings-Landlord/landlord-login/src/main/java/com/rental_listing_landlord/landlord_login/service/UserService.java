package com.rental_listing_landlord.landlord_login.service;

import com.rental_listing_landlord.landlord_login.entity.User;
import com.rental_listing_landlord.landlord_login.repository.UserRepository;

public interface UserService {

	public User registerIfNotExists(String email,String password, String name);
	
	public boolean isLocked(User user);
	
	public boolean validatePasswordAndUpdateAttempts(User user, String password);
	
	public User findByUserName(String email);
	
}
