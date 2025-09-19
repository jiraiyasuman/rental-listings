package com.rental_listing_landlord.landlord_post.dto;

public class PaymentDto {

	private boolean payment;

	private String postId;

	public boolean isPayment() {
		return payment;
	}

	public void setPayment(boolean payment) {
		this.payment = payment;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public PaymentDto(boolean payment, String postId) {
		super();
		this.payment = payment;
		this.postId = postId;
	}

	public PaymentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PaymentDto [payment=" + payment + ", postId=" + postId + "]";
	}

}
