package com.rental_listing_landlord.landlord_post.dto;

public class PaymentDto {

	private boolean payment;

	public boolean isPayment() {
		return payment;
	}

	public void setPayment(boolean payment) {
		this.payment = payment;
	}

	public PaymentDto(boolean payment) {
		super();
		this.payment = payment;
	}

	public PaymentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
