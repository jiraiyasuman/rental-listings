package com.rental_listing_landlord.landlord_post.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity(name="property_posting")
public class PropertyPosting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="owner_name")
	private String ownerName;
	@Column(name="owner_email")
	private String ownerEmail;
	@Column(name="owner_mobile")
	private String ownerMobile;
	@Column(name="property_address")
	private String propertyAddress;
	@Column(name="property_name")
	private String propertyName;
	@Column(name="property_type")
	private String propertyType;
	@Column(name="property_classification")
	private String propertyClassification;
	@Column(name="property_category")
	private String propertyCategory;
	@Column(name="rent")
	private double rent;
	@Column(name="advance")
	private double advance;
	@Column(name="post_id")
	private String postId;
	@Column(name="status")
	private String status;
	@Column(name="benefits")
	private String benefits;
	@Column(name="payment")
	private boolean payment;
	
	public boolean isPayment() {
		return payment;
	}
	public void setPayment(boolean payment) {
		this.payment = payment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBenefits() {
		return benefits;
	}
	public void setBenefits(String benefits) {
		this.benefits = benefits;
	}
	public PropertyPosting(int id, String ownerName, String ownerEmail, String ownerMobile, String propertyAddress,
			String propertyName, String propertyType, String propertyClassification, String propertyCategory,
			double rent, double advance, String postId, String status, String benefits) {
		super();
		this.id = id;
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
		this.postId = postId;
		this.status = status;
		this.benefits = benefits;
	}
	public PropertyPosting() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PropertyPosting [id=" + id + ", ownerName=" + ownerName + ", ownerEmail=" + ownerEmail
				+ ", ownerMobile=" + ownerMobile + ", propertyAddress=" + propertyAddress + ", propertyName="
				+ propertyName + ", propertyType=" + propertyType + ", propertyClassification=" + propertyClassification
				+ ", propertyCategory=" + propertyCategory + ", rent=" + rent + ", advance=" + advance + ", postId="
				+ postId + ", status=" + status + ", benefits=" + benefits + "]";
	}
	
}
