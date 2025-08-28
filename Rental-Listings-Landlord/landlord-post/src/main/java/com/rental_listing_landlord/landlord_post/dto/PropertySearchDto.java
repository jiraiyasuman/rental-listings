package com.rental_listing_landlord.landlord_post.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class PropertySearchDto {

	@Email
	@Schema(description = "Email Id of the Owner")
	@NotEmpty(message = "Owner Email should not be empty/white-space/null")
	private String ownerEmail;

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public PropertySearchDto(
			@Email @NotEmpty(message = "Owner Email should not be empty/white-space/null") String ownerEmail) {
		super();
		this.ownerEmail = ownerEmail;
	}

	public PropertySearchDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
