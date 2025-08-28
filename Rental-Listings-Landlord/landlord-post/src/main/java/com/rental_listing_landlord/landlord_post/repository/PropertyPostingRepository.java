package com.rental_listing_landlord.landlord_post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rental_listing_landlord.landlord_post.entity.PropertyPosting;

public interface PropertyPostingRepository extends JpaRepository<PropertyPosting, Integer>{

	public PropertyPosting getPropertyPostingByPostId(String postId);
	
	public List<PropertyPosting> getPropertyPostingByOwnerEmail(String ownerEmail);
	
	public List<PropertyPosting> getPropertyPostingByStatus(String status);
	
}
