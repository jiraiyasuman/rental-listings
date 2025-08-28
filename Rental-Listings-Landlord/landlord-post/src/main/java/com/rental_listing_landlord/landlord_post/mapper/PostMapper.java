package com.rental_listing_landlord.landlord_post.mapper;

import com.rental_listing_landlord.landlord_post.dto.PropertyPostDto;
import com.rental_listing_landlord.landlord_post.entity.PropertyPosting;

public class PostMapper {

	
	public PropertyPostDto mapToPropertyPostDto(PropertyPosting propertyPosting) {
	PropertyPostDto propertyPostDto = new PropertyPostDto();
	propertyPostDto.setOwnerEmail(propertyPosting.getOwnerEmail());
	propertyPostDto.setOwnerMobile(propertyPosting.getOwnerMobile());
	propertyPostDto.setOwnerName(propertyPosting.getOwnerName());
	propertyPostDto.setPropertyAddress(propertyPosting.getPropertyAddress());
	propertyPostDto.setPropertyCategory(propertyPosting.getPropertyCategory());
	propertyPostDto.setPropertyClassification(propertyPosting.getPropertyClassification());
	propertyPostDto.setPropertyName(propertyPosting.getPropertyName());
	propertyPostDto.setPropertyType(propertyPosting.getPropertyType());
	propertyPostDto.setRent(propertyPosting.getRent());
	propertyPostDto.setAdvance(propertyPosting.getAdvance());
	return propertyPostDto;
	}
	
	public PropertyPosting mapToPropertyPosting(PropertyPostDto propertyPostDto) {
		PropertyPosting propertyPosting = new PropertyPosting();
		propertyPosting.setOwnerEmail(propertyPostDto.getOwnerEmail());
		propertyPosting.setOwnerMobile(propertyPostDto.getOwnerMobile());
		propertyPosting.setOwnerName(propertyPostDto.getOwnerName());
		propertyPosting.setPropertyAddress(propertyPostDto.getPropertyAddress());
		propertyPosting.setPropertyCategory(propertyPostDto.getPropertyCategory());
		propertyPosting.setPropertyClassification(propertyPostDto.getPropertyClassification());
		propertyPosting.setPropertyName(propertyPostDto.getPropertyName());
		propertyPosting.setPropertyType(propertyPostDto.getPropertyType());
		propertyPosting.setRent(propertyPostDto.getRent());
		propertyPosting.setAdvance(propertyPostDto.getAdvance());
		return propertyPosting;
	}
}
