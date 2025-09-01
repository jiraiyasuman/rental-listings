package com.rental_listing_landlord.landlord_post.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.rental_listing_landlord.landlord_post.entity.PropertyPhotos;

public interface PropertyPhotoService {

	public Boolean savePropertyPhootos(PropertyPhotos propertyPhotos);
	
	public Boolean uploadFile(MultipartFile file) throws IOException;
	
	public String uploadFileWithinData(MultipartFile file)throws IOException;
	
}
