package com.tenant_payment_gateway.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;

public class PaymentOrderDto {

	@Schema(name="Name of the payer")
	@NotEmpty(message="Name of the person cannot be blank/empty/null")
	private String name;
	@Schema(name="Email of the payer")
	@NotEmpty(message="Email of the person cannot be blank/empty/null")
	private String email;
	@Schema(name="Mobile of the payer")
	@NotEmpty(message = "Mobile of the person cannot be blank/empty/null")
	private String mobile;
	@NotEmpty(message="Booking Id of the person cannot be blank/empty/null")
	@Schema(name="Booking ID of the payer")
	private String bookingId;
	@Schema(name="Property Name of the landlord")
	@NotEmpty(message="Property Name cannot be blank/empty/null")
	private String propertyName;
	@NotEmpty(message = "Amount paid by the person cannot be blank/empty/null")
	@Schema(name="Amount paid by the tenant")
	private double amount;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public PaymentOrderDto(@NotEmpty(message = "Name of the person cannot be blank/empty/null") String name,
			@NotEmpty(message = "Email of the person cannot be blank/empty/null") String email,
			@NotEmpty(message = "Mobile of the person cannot be blank/empty/null") String mobile,
			@NotEmpty(message = "Booking Id of the person cannot be blank/empty/null") String bookingId,
			@NotEmpty(message = "Property Name cannot be blank/empty/null") String propertyName,
			@NotEmpty(message = "Amount paid by the person cannot be blank/empty/null") double amount) {
		super();
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.bookingId = bookingId;
		this.propertyName = propertyName;
		this.amount = amount;
	}
	public PaymentOrderDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PaymentOrderDto [name=" + name + ", email=" + email + ", mobile=" + mobile + ", bookingId=" + bookingId
				+ ", propertyName=" + propertyName + ", amount=" + amount + "]";
	}
	
}
