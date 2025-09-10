package com.rental_listing.tenant_booking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="booking")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="address")
	private String address;
	@Column(name="age_years")
	private int ageYears;
	@Column(name="age_months")
	private int ageMonths;
	@Column(name="email")
	private String email;
	@Column(name="mobile")
	private int mobile;
	@Column(name="property_name")
	private String propertyName;
	@Column(name="property_type")
	private String propertyType;
	@Column(name="property_classification")
	private String propertyClassification;
	@Column(name="property_category")
	private String propertyCategory;
	@Column(name="owner_name")
	private String ownerName;
	@Column(name="booking_id")
	private String bookingId;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAgeYears() {
		return ageYears;
	}
	public void setAgeYears(int ageYears) {
		this.ageYears = ageYears;
	}
	public int getAgeMonths() {
		return ageMonths;
	}
	public void setAgeMonths(int ageMonths) {
		this.ageMonths = ageMonths;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getMobile() {
		return mobile;
	}
	public void setMobile(int mobile) {
		this.mobile = mobile;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	public String getPropertyClassification() {
		return propertyClassification;
	}
	public void setPropertyClassification(String propertyClassification) {
		this.propertyClassification = propertyClassification;
	}
	public String getPropertyCategory() {
		return propertyCategory;
	}
	public void setPropertyCategory(String propertyCategory) {
		this.propertyCategory = propertyCategory;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public Booking(int id, String name, String address, int ageYears, int ageMonths, String email, int mobile,
			String propertyName, String propertyType, String propertyClassification, String propertyCategory,
			String ownerName, String bookingId) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.ageYears = ageYears;
		this.ageMonths = ageMonths;
		this.email = email;
		this.mobile = mobile;
		this.propertyName = propertyName;
		this.propertyType = propertyType;
		this.propertyClassification = propertyClassification;
		this.propertyCategory = propertyCategory;
		this.ownerName = ownerName;
		this.bookingId = bookingId;
	}
	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Booking [id=" + id + ", name=" + name + ", address=" + address + ", ageYears=" + ageYears
				+ ", ageMonths=" + ageMonths + ", email=" + email + ", mobile=" + mobile + ", propertyName="
				+ propertyName + ", propertyType=" + propertyType + ", propertyClassification=" + propertyClassification
				+ ", propertyCategory=" + propertyCategory + ", ownerName=" + ownerName + ", bookingId=" + bookingId
				+ "]";
	}
	
	
}
