package com.tenant_payment_gateway.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity(name="payment_order")
public class PaymentOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="email")
	private String email;
	@Column(name="mobile")
	private String mobile;
	@Column(name="booking_id")
	private String bookingId;
	@Column(name="property_name")
	private String propertyName;
	@Column(name="amount")
	private double amount;
	@Column(name="order_status")
	private String orderStatus;
	@Column(name="rayzorpay_order_id")
	private String rayzorpayOrderId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getRayzorpayOrderId() {
		return rayzorpayOrderId;
	}
	public void setRayzorpayOrderId(String rayzorpayOrderId) {
		this.rayzorpayOrderId = rayzorpayOrderId;
	}
	public PaymentOrder( String name, String email, String mobile, String bookingId, String propertyName,
			double amount, String orderStatus, String rayzorpayOrderId) {
		super();
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.bookingId = bookingId;
		this.propertyName = propertyName;
		this.amount = amount;
		this.orderStatus = orderStatus;
		this.rayzorpayOrderId = rayzorpayOrderId;
	}
	public PaymentOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PaymentOrder [id=" + id + ", name=" + name + ", email=" + email + ", mobile=" + mobile + ", bookingId="
				+ bookingId + ", propertyName=" + propertyName + ", amount=" + amount + ", orderStatus=" + orderStatus
				+ ", rayzorpayOrderId=" + rayzorpayOrderId + "]";
	}
	
}
