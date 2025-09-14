package com.rental_listing_landlord.landlord_login.mapper;

import com.rental_listing_landlord.landlord_login.dto.LoginDto;
import com.rental_listing_landlord.landlord_login.entity.User;

public class LoginMapper {

	public User mapToLogin(LoginDto loginDto) {
		User login = new User();
		login.setEmail(loginDto.getEmail());
		login.setName(loginDto.getName());
		login.setPassword(loginDto.getPassword());
		return login;
	}
	
	public LoginDto mapToLoginDto(User login) {
		LoginDto loginDto = new LoginDto();
		loginDto.setEmail(login.getEmail());
		loginDto.setName(login.getName());
		loginDto.setPassword(login.getPassword());
		return loginDto;
	}
}
