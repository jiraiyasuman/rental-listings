package com.rental_listing_landlord.landlord_post.dto;

public class StatusDto {

	private String status;

	public StatusDto(String status) {
		super();
		this.status = status;
	}

	public StatusDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
