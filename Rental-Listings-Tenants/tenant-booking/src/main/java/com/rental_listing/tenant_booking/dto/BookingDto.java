package com.rental_listing.tenant_booking.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;

public class BookingDto {

	@Schema(name="Name of the Tenant")
	@NotEmpty(message = "Name of the tenant should not be blank/empty/null")
	private String name;
	@Schema(name="Address of the Tenant")
	@NotEmpty(message = "Address of the tenant should not be blank/empty/null")
	private String address;
	@Schema(name="Age of the tenant in years")
	@NotEmpty(message = "Age of the tenant in years should not be blank/empty/null")
	private int ageYears;
	@Schema(name="Age of the tenant in months")
	@NotEmpty(message="Age of the tenant in months should not be blank/empty/null")
	private int ageMonths;
	@Schema(name="Email of the tenant ")
	@NotEmpty(message="Email should not be blank/empty/null")
	private String email;
	@Schema(name="Mobile Number of the tenant")
	@NotEmpty(message = "Mobile Number should not be blank/empty/null")
	private int mobile;
	@Schema(name="Property Name of the tenant")
	@NotEmpty(message="Property Name should not be blank/empty/null")
	private String propertyName;
	@Schema(name="Property Type of the tenant")
	@NotEmpty(message="Property Type should not be blank/empty/null")
	private String propertyType;
	@Schema(name="Property Classification of the tenant")
	@NotEmpty(message="Property Classification should not be blank/empty/null")
	private String propertyClassification;
	@Schema(name="Property Category of the tenant")
	@NotEmpty(message="Property Category should not be blank/empty/null")
	private String propertyCategory;
	@Schema(name="Owner Name of the Property")
	@NotEmpty(message = "Owner Name should not be blank/empty/null")
	private String ownerName;
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
	public BookingDto(@NotEmpty(message = "Name of the tenant should not be blank/empty/null") String name,
			@NotEmpty(message = "Address of the tenant should not be blank/empty/null") String address,
			@NotEmpty(message = "Age of the tenant in years should not be blank/empty/null") int ageYears,
			@NotEmpty(message = "Age of the tenant in months should not be blank/empty/null") int ageMonths,
			@NotEmpty(message = "Email should not be blank/empty/null") String email,
			@NotEmpty(message = "Mobile Number should not be blank/empty/null") int mobile,
			@NotEmpty(message = "Property Name should not be blank/empty/null") String propertyName,
			@NotEmpty(message = "Property Type should not be blank/empty/null") String propertyType,
			@NotEmpty(message = "Property Classification should not be blank/empty/null") String propertyClassification,
			@NotEmpty(message = "Property Category should not be blank/empty/null") String propertyCategory,
			@NotEmpty(message = "Owner Name should not be blank/empty/null") String ownerName) {
		super();
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
	}
	public BookingDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "BookingDto [name=" + name + ", address=" + address + ", ageYears=" + ageYears + ", ageMonths="
				+ ageMonths + ", email=" + email + ", mobile=" + mobile + ", propertyName=" + propertyName
				+ ", propertyType=" + propertyType + ", propertyClassification=" + propertyClassification
				+ ", propertyCategory=" + propertyCategory + ", ownerName=" + ownerName + "]";
	}
	
}
