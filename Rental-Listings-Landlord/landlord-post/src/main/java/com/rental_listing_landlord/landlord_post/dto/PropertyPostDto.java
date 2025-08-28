package com.rental_listing_landlord.landlord_post.dto;

import org.antlr.v4.runtime.misc.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class PropertyPostDto {

	@Schema(description  = "Owner_Name")
	@NotEmpty(message = "Owner Name should not be empty/white-space/null")
	private String ownerName;
	@Schema(description = "Owner Email")
	@Email
	@NotEmpty(message = "Owner Email should not be empty/white-space/null")
	private String ownerEmail;
	@Schema(name="Owner Mobile Number")
	@NotEmpty(message = "Owner Mobile Number should not be empty/white-space/null")
	private String ownerMobile;
	@Schema(name="Property Address")
	@NotEmpty(message = "Property Address should not be empty/white-space/null")
	private String propertyAddress;
	@Schema(name="Property Name")
	@NotEmpty(message = "Property Name should not be empty/white-space/null")
	private String propertyName;
	@Schema(name="Property Type")
	@NotEmpty(message = "Property type should not be empty/white-space/null")
	private String propertyType;
	@Schema(name="Property Classification")
	@NotEmpty(message = "Property Classification should not be empty/white-space/null")
	private String propertyClassification;
	@Schema(name="Property Category")
	@NotEmpty(message = "Property Category should not be empty/white-space/null")
	private String propertyCategory;
	@Schema(name="Rent")
	@NotEmpty(message="Rent should not be empty/white-space/null")
	private double rent;
	@Schema(name="Advance")
	@NotEmpty(message="Advance should not be empty/white-space/null")
	private double advance;
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getOwnerEmail() {
		return ownerEmail;
	}
	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
	public String getOwnerMobile() {
		return ownerMobile;
	}
	public void setOwnerMobile(String ownerMobile) {
		this.ownerMobile = ownerMobile;
	}
	public String getPropertyAddress() {
		return propertyAddress;
	}
	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
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
	public double getRent() {
		return rent;
	}
	public void setRent(double rent) {
		this.rent = rent;
	}
	public double getAdvance() {
		return advance;
	}
	public void setAdvance(double advance) {
		this.advance = advance;
	}
	public PropertyPostDto(@NotEmpty(message = "Owner Name should not be empty/white-space/null") String ownerName,
			@Email @NotEmpty(message = "Owner Email should not be empty/white-space/null") String ownerEmail,
			@NotEmpty(message = "Owner Mobile Number should not be empty/white-space/null") String ownerMobile,
			@NotEmpty(message = "Property Address should not be empty/white-space/null") String propertyAddress,
			@NotEmpty(message = "Property Name should not be empty/white-space/null") String propertyName,
			@NotEmpty(message = "Property type should not be empty/white-space/null") String propertyType,
			@NotEmpty(message = "Property Classification should not be empty/white-space/null") String propertyClassification,
			@NotEmpty(message = "Property Category should not be empty/white-space/null") String propertyCategory,
			@NotEmpty(message = "Rent should not be empty/white-space/null") double rent,
			@NotEmpty(message = "Advance should not be empty/white-space/null") double advance) {
		super();
		this.ownerName = ownerName;
		this.ownerEmail = ownerEmail;
		this.ownerMobile = ownerMobile;
		this.propertyAddress = propertyAddress;
		this.propertyName = propertyName;
		this.propertyType = propertyType;
		this.propertyClassification = propertyClassification;
		this.propertyCategory = propertyCategory;
		this.rent = rent;
		this.advance = advance;
	}
	public PropertyPostDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PropertyPostDto [ownerName=" + ownerName + ", ownerEmail=" + ownerEmail + ", ownerMobile=" + ownerMobile
				+ ", propertyAddress=" + propertyAddress + ", propertyName=" + propertyName + ", propertyType="
				+ propertyType + ", propertyClassification=" + propertyClassification + ", propertyCategory="
				+ propertyCategory + ", rent=" + rent + ", advance=" + advance + "]";
	}
	
}
