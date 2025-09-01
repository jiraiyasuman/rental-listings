package com.rental_listing_landlord.landlord_post.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
@Entity(name="property_photos")
public class PropertyPhotos {

	@Id
	@Column(name="post_id")
	private String postId;
	@Column(name="first_image")
	private String firstImage;
	@Column(name="second_image")
	private String secondImage;
	@Column(name="third_image")
	private String thirdImage;
	@Column(name="fourth_name")
	private String fourthImage;
	@Column(name="fifth_image")
	private String fifthImage;
	@OneToOne
    @MapsId   // Tells Hibernate to use the same PK as PropertyPosting
    @JoinTable(
        name = "posting_photos_mapping",
        joinColumns = @JoinColumn(name = "photo_post_id", referencedColumnName = "postId"),
        inverseJoinColumns = @JoinColumn(name = "posting_id", referencedColumnName = "postId")
    )
    private PropertyPosting propertyPosting;
	public PropertyPhotos(String postId, String firstImage, String secondImage, String thirdImage, String fourthImage,
			String fifthImage) {
		super();
		this.postId = postId;
		this.firstImage = firstImage;
		this.secondImage = secondImage;
		this.thirdImage = thirdImage;
		this.fourthImage = fourthImage;
		this.fifthImage = fifthImage;
	}
	public PropertyPhotos() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return postId;
	}
	public void setId(String id) {
		this.postId = id;
	}
	public String getFirstImage() {
		return firstImage;
	}
	public void setFirstImage(String firstImage) {
		this.firstImage = firstImage;
	}
	public String getSeconsImage() {
		return secondImage;
	}
	public void setSeconsImage(String seconsImage) {
		this.secondImage = seconsImage;
	}
	public String getThirdImage() {
		return thirdImage;
	}
	public void setThirdImage(String thirdImage) {
		this.thirdImage = thirdImage;
	}
	public String getFourthImage() {
		return fourthImage;
	}
	public void setFourthImage(String fourthImage) {
		this.fourthImage = fourthImage;
	}
	public String getFifthImage() {
		return fifthImage;
	}
	public void setFifthImage(String fifthImage) {
		this.fifthImage = fifthImage;
	}
	@Override
	public String toString() {
		return "PropertyPhotos [postId=" + postId + ", firstImage=" + firstImage + ", secondImage=" + secondImage
				+ ", thirdImage=" + thirdImage + ", fourthImage=" + fourthImage + ", fifthImage=" + fifthImage + "]";
	}
	
}
