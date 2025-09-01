package com.rental_listing_landlord.landlord_post.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.rental_listing_landlord.landlord_post.entity.PropertyPosting;
import com.rental_listing_landlord.landlord_post.exception.DuplicateEntryException;

public interface PropertyPostServices {

	public PropertyPosting addProperties(PropertyPosting propertyPosting) throws DuplicateEntryException; // adds a new post 
	
	public PropertyPosting updateProperties(String postId, PropertyPosting propertyPosting); // updates a post
	
	public PropertyPosting updateStatusProperties(String postId, String status); // updates the status of the post 
	
	public PropertyPosting updatePaymentStatusProperties(String postId,boolean payment);
	
	public List<PropertyPosting> viewAllProperties(); // displays the posts
	
	public List<PropertyPosting> viewIndividualOwnerPosting(String ownerEmail); // displays the post made by the user
	
	public PropertyPosting getPropertyById(int id); // displays the details of a post made by an user
	
	public void deletePropertyById(int id) ; // deletes a post by user
	
	public Page<PropertyPosting> findPaginatedAllDetails(int pageNo, int pageSize, String sortField, String sortDirection);
	
	
}
