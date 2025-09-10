package com.rental_listing_landlord.landlord_post.servicesimpl;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rental_listing_landlord.landlord_post.entity.PropertyPosting;
import com.rental_listing_landlord.landlord_post.exception.DuplicateEntryException;
import com.rental_listing_landlord.landlord_post.exception.NotFoundException;
import com.rental_listing_landlord.landlord_post.repository.PropertyPostingRepository;
import com.rental_listing_landlord.landlord_post.services.PropertyPostServices;
@Service
public class PropertyPostServicesImpl implements PropertyPostServices{

	private PropertyPostingRepository propertyPostingRepository;
	private Logger LOGGER = Logger.getLogger(getClass().getName());
	private JavaMailSender javaMailSender;
	@Autowired
	public PropertyPostServicesImpl(PropertyPostingRepository propertyPostingRepository) {
		super();
		this.propertyPostingRepository = propertyPostingRepository;
	}

	@Transactional
	@Override
	public PropertyPosting addProperties(PropertyPosting propertyPosting) throws DuplicateEntryException {
		propertyPosting.setStatus("Open");
		propertyPosting.setPayment(false);
		boolean status = checkForDuplicateEntry(propertyPosting);
		if(status==false) {
			PropertyPosting savedPosting = propertyPostingRepository.save(propertyPosting);
			LOGGER.info("Property Details inserted successfully");
			String to = propertyPosting.getOwnerEmail();
			String subject ="Property Posting Confirmation";
			String message="Dear Owner,<br> Congratulations! This is to inform that the posting has been successful and your posting id is"+propertyPosting.getPostId()+"<br> Thanks and Regards,<br>Team Online Rental";
			sendEmail(to, subject, message);
			return savedPosting;	
		}else {
			LOGGER.warning("Duplicate entry found!");
			throw new DuplicateEntryException("Duplicate entry is found!");
		}
		
	}

	public boolean checkForDuplicateEntry(PropertyPosting propertyPosting) {
		PropertyPosting check = propertyPostingRepository.getPropertyPostingByPostId(propertyPosting.getPostId());
		if(check == null) {
			return false; // No duplicate entries found
		}
		return true;
	}
	@Transactional
	@Override
	public PropertyPosting updateProperties(String postId, PropertyPosting propertyPosting) {
		PropertyPosting fetchPropertyPosting = propertyPostingRepository.getPropertyPostingByPostId(postId);
		if(fetchPropertyPosting == null) {
			LOGGER.warning("There is no details found with this id");
			throw new NotFoundException("No details found with this id");
		}
		fetchPropertyPosting.setAdvance(propertyPosting.getAdvance());
		fetchPropertyPosting.setBenefits(propertyPosting.getBenefits());
		fetchPropertyPosting.setOwnerEmail(propertyPosting.getOwnerEmail());
		fetchPropertyPosting.setOwnerMobile(propertyPosting.getOwnerMobile());
		fetchPropertyPosting.setOwnerName(propertyPosting.getOwnerName());
		fetchPropertyPosting.setPayment(false);
		fetchPropertyPosting.setPostId(fetchPropertyPosting.getPostId());
		fetchPropertyPosting.setPropertyAddress(propertyPosting.getPropertyAddress());
		fetchPropertyPosting.setPropertyCategory(propertyPosting.getPropertyCategory());
		fetchPropertyPosting.setPropertyClassification(propertyPosting.getPropertyClassification());
		fetchPropertyPosting.setPropertyName(propertyPosting.getPropertyName());
		fetchPropertyPosting.setPropertyType(propertyPosting.getPropertyType());
		fetchPropertyPosting.setRent(propertyPosting.getRent());
		fetchPropertyPosting.setStatus(propertyPosting.getStatus());
		fetchPropertyPosting.setId(fetchPropertyPosting.getId());
		PropertyPosting updatedPropertyPosting = propertyPostingRepository.save(fetchPropertyPosting);
		LOGGER.info("Property Details updated successfully");
		return updatedPropertyPosting;
	}

	@Transactional
	@Override
	public PropertyPosting updateStatusProperties(String postId, String status) {
		PropertyPosting fetchPropertyPosting = propertyPostingRepository.getPropertyPostingByPostId(postId);
		if(fetchPropertyPosting == null) {
			LOGGER.warning("There is no details found with this id");
			throw new NotFoundException("No details found with this id");
		}
		fetchPropertyPosting.setStatus(status);
		PropertyPosting updatedPropertyPosting = propertyPostingRepository.save(fetchPropertyPosting);
		LOGGER.info("Property status updated succesfully");
		return updatedPropertyPosting;
	}

	@Override
	public List<PropertyPosting> viewAllProperties() {
		List<PropertyPosting> getAllDetails = propertyPostingRepository.findAll();
		LOGGER.info("All Property Details are being fetched successfully");
		return getAllDetails;
	}

	@Override
	public List<PropertyPosting> viewIndividualOwnerPosting(String ownerEmail) {
		List<PropertyPosting> getDetailsByEmail = propertyPostingRepository.getPropertyPostingByOwnerEmail(ownerEmail);
		LOGGER.info("Property Details by email id are being fetched successfully");
		return getDetailsByEmail;
	}

	@Override
	public PropertyPosting getPropertyById(int id) {
		PropertyPosting fetchPropertyPosting = propertyPostingRepository.findById(id).get();
		if(fetchPropertyPosting == null) {
			LOGGER.warning("There is no details found with this id");
			throw new NotFoundException("No details found with this id");
		}
		LOGGER.info("Property Details by id is being fetched successfully");
		return fetchPropertyPosting;
	}

	@Transactional
	@Override
	public void deletePropertyById(int id) {
		PropertyPosting fetchPropertyPosting = propertyPostingRepository.findById(id).get();
		if(fetchPropertyPosting == null) {
			LOGGER.warning("There is no details found with this id");
			throw new NotFoundException("No details found with this id");
		}
		LOGGER.info("Property Details by id is being deleted successfully");
		propertyPostingRepository.deleteById(id);
		
	}

	@Transactional
	@Override
	public PropertyPosting updatePaymentStatusProperties(String postId, boolean payment) {
		PropertyPosting fetchPropertyPosting = propertyPostingRepository.getPropertyPostingByPostId(postId);
		if(fetchPropertyPosting == null) {
			LOGGER.warning("There is no details found with this id");
			throw new NotFoundException("No details found with this id");
		}
		fetchPropertyPosting.setPayment(payment);
		PropertyPosting updatePropertyPosting = propertyPostingRepository.save(fetchPropertyPosting);
		return updatePropertyPosting;
	}

	@Override
	public Page<PropertyPosting> findPaginatedAllDetails(int pageNo, int pageSize, String sortField,
			String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending():
			Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo-1, pageSize,sort);
		return this.propertyPostingRepository.findAll(pageable);
	}
	
	public void sendEmail(String to,String subject,String message) {
		try {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setTo(to);
			simpleMailMessage.setSubject(subject);
			simpleMailMessage.setText(message);
			javaMailSender.send(simpleMailMessage);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
