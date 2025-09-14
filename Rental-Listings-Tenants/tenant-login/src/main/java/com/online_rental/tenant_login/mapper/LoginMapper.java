package com.online_rental.tenant_login.mapper;

import com.online_rental.tenant_login.dto.LoginDto;
import com.online_rental.tenant_login.entity.Login;

public class LoginMapper {

	public Login mapToLogin(LoginDto loginDto) {
		Login login = new Login();
		login.setEmail(loginDto.getEmail());
		login.setName(loginDto.getName());
		login.setPassword(loginDto.getPassword());
		return login;
	}
	
	public LoginDto mapToLoginDto(Login login) {
		LoginDto loginDto = new LoginDto();
		loginDto.setEmail(login.getEmail());
		loginDto.setName(login.getName());
		loginDto.setPassword(login.getPassword());
		return loginDto;
	}
}
