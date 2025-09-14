package com.rental_listing_landlord.landlord_login.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class LoginDto {

	@Schema(name="Name of the User")
	@NotEmpty(message = "Name of the tenant should not be blank/null/empty")
	private String name;
	@Schema(name="Email of the user")
	@NotEmpty(message="Email of the tenant should not be blank/null/empty")
	@Email
	private String email;
	@Schema(name="Password of the user")
	@NotEmpty(message="Password of the tenant should not be blank/null/empty")
	private String password;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LoginDto(@NotEmpty(message = "Name of the tenant should not be blank/null/empty") String name,
			@NotEmpty(message = "Email of the tenant should not be blank/null/empty") @Email String email,
			@NotEmpty(message = "Password of the tenant should not be blank/null/empty") String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}
	public LoginDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "LoginDto [name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	
}
