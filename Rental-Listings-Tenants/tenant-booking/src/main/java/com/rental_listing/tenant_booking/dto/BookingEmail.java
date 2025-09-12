package com.rental_listing.tenant_booking.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class BookingEmail {

	@Schema(name="Email of the tenant")
	@Email@NotEmpty(message="Email should not be null/empty/blank")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BookingEmail() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "BookingEmail [email=" + email + "]";
	}
	
	
}
