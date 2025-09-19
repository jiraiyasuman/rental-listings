package com.rental_listing_landlord.landlord_post.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rental_listing_landlord.landlord_post.dto.PaymentDto;
import com.rental_listing_landlord.landlord_post.dto.PropertyPostDto;
import com.rental_listing_landlord.landlord_post.dto.PropertySearchDto;
import com.rental_listing_landlord.landlord_post.dto.StatusDto;
import com.rental_listing_landlord.landlord_post.entity.PropertyPosting;
import com.rental_listing_landlord.landlord_post.mapper.PostMapper;
import com.rental_listing_landlord.landlord_post.services.PropertyPostServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/propertypost")
@Tag(
		name ="CRUD REST API Microservice component for landlord property posting",
		description = "Create Post, Get Post(s), Update Post, Delete Post"
		)
public class PostPropertyController {

	private PropertyPostServices propertyPostServices;
	private PostMapper postMapper = new PostMapper();
	private Logger LOGGER = Logger.getLogger(getClass().getName());
	@Autowired
	public PostPropertyController(PropertyPostServices propertyPostServices) {
		super();
		this.propertyPostServices = propertyPostServices;
	}
	// Post Mapping for insertion for the property posts
	// http://localhost:8080/api/v1/propertyhost/save
	@PostMapping("save")
	@Operation(
			summary = "Create Property REST API",
			description = "Create Property REST API is used to save property details in a database  "
			)  
	@ApiResponse(
			responseCode = "201",
			description = "HTTP STATUS 201 created"
			)
	public ResponseEntity<PropertyPostDto> savePropertyPost(@Valid @RequestBody PropertyPostDto propertyPostDto){
		try {
			PropertyPosting propertyPosting = postMapper.mapToPropertyPosting(propertyPostDto);
			Random random = new Random();
			int number = 1000+random.nextInt(9000);
			String code = "PRO"+String.valueOf(number);
			propertyPosting.setPostId(code);
			PropertyPosting savedPropertyPosting = propertyPostServices.addProperties(propertyPosting);
			PropertyPostDto savedPropertyPostDto = postMapper.mapToPropertyPostDto(savedPropertyPosting);
		    LOGGER.info("Property post save property is being executed");
		    return ResponseEntity.ok(savedPropertyPostDto);
		} catch (Exception e) {
			System.err.println(e);
		}
		return ResponseEntity.ok(null);
	}
	// Get all post details
	// http://localhost:8080/api/v1/propertyhost/listAll
	@Operation(
			summary ="Get Property REST API",
			description="GET all the property details"
			)
	@ApiResponse(
			responseCode = "201",
			description = "HTTP STATUS 201 created"
			)
	
	@GetMapping("listAll")
	public ResponseEntity<Map<String, Object>> getAllPostDetails() {
	    return findPaginatedAllDetails(1, 10, "ownerName", "asc");
	}

	@GetMapping("listAll/page")
	@Operation(
	        summary = "Get Property REST API paginated data",
	        description = "GET all the property details paginated data"
	)
	@ApiResponse(
	        responseCode = "200",
	        description = "HTTP STATUS 200 OK"
	)
	public ResponseEntity<Map<String, Object>> findPaginatedAllDetails(
	        @RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "10") int limit,
	        @RequestParam(defaultValue = "ownerName") String sortField,
	        @RequestParam(defaultValue = "asc") String sortDir
	) {
	    Page<PropertyPosting> propertyPage =
	            propertyPostServices.findPaginatedAllDetails(page, limit, sortField, sortDir);

	    Map<String, Object> response = new HashMap<>();
	    response.put("currentPage", page);
	    response.put("totalElements", propertyPage.getTotalElements());
	    response.put("totalPages", propertyPage.getTotalPages());
	    response.put("sortField", sortField);
	    response.put("sortDir", sortDir);
	    response.put("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
	    response.put("data", propertyPage.getContent());

	    LOGGER.info("Property Details pagination executed successfully");
	    return ResponseEntity.ok(response);
	}
	// fetch the property details with respect to email
	//http://localhost:8080/propertyhost/listSearchByEmail
	@Operation(
			summary="Search Property details by Owners Email REST API",
			description="Search Property Details By Owners Email Id is used to find the property deatils by their email id"
			)
	@ApiResponse(
			responseCode = "201",
			description="HTTP STATUS 201 CREATED"
			)
	@GetMapping("listSearchByEmail")
	public ResponseEntity<List<PropertyPosting>> getPropertyPostByEmail(@Valid @RequestBody PropertySearchDto propertySearchDto){
		List<PropertyPosting> getPropertyByEmail = propertyPostServices.viewIndividualOwnerPosting(propertySearchDto.getOwnerEmail());
		LOGGER.info("List Search By Email executed successfully");
		return ResponseEntity.ok(getPropertyByEmail);
	}
	// fetch property details by id
	//http://localhost:8080/propertyhost/listAll/{id}
	@Operation(
			summary="Search Property Details by Id REST API",
			description="Search Property Details By ID is used to find the property details by id"
			)
	@ApiResponse(
			responseCode = "201",
			description = "HTTP STATUS 201 CREATED"
			)
	@GetMapping("listAll/{id}")
	public ResponseEntity<PropertyPosting> getPropertyById(@PathVariable("id") int id){
		PropertyPosting propertyPosting = propertyPostServices.getPropertyById(id);
		LOGGER.info("Get Details By id fetched successfully");
		return ResponseEntity.ok(propertyPosting);
	}
	// update property details by id
		//http://localhost:8080/propertyhost/update/{postId}
		@Operation(
				summary="Update Property Details by Id REST API",
				description="Update Property Details By ID is used to update the property details by id"
				)
		@ApiResponse(
				responseCode = "201",
				description = "HTTP STATUS 201 CREATED"
				)
	@PutMapping("update/{postId}")
	public ResponseEntity<PropertyPostDto> updatePropertyDetails(@Valid @PathVariable("postId") String postId, @RequestBody PropertyPostDto propertyPostDto){
	    PropertyPosting propertyPosting = postMapper.mapToPropertyPosting(propertyPostDto);
	    PropertyPosting updatedPosting = propertyPostServices.updateProperties(postId, propertyPosting);
	    PropertyPostDto updatedPropertyPostDto = postMapper.mapToPropertyPostDto(updatedPosting);
	    LOGGER.info("Updation of Property details is done successfully");
	    return ResponseEntity.ok(updatedPropertyPostDto);
	}
		// delete property details by id
		//http://localhost:8080/propertyhost/delete/{id}
		@Operation(
				summary="Delete Property Details by Id REST API",
				description="Delete Property Details By ID is used to delete the property details by id"
				)
		@ApiResponse(
				responseCode = "201",
				description = "HTTP STATUS 201 CREATED"
				)
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deletePropertyDetails(@PathVariable("id") int id){
		propertyPostServices.deletePropertyById(id);
		LOGGER.info("Deletion of Property Detail is done successfully");
		return ResponseEntity.ok("Deletion of data is successful");
	}
		// fetch property details by id
		//http://localhost:8080/propertyhost/updateStatus/{postId}
		@Operation(
				summary="Update Property Details status by Id REST API",
				description="Update Property Details status By ID is used to update the property details by id"
				)
		@ApiResponse(
				responseCode = "201",
				description = "HTTP STATUS 201 CREATED"
				)
	@PutMapping("updateStatus/{postId}")
	public ResponseEntity<PropertyPostDto> updatePropertyStatus(@PathVariable("postId") String postId, StatusDto statusDto ){
	   PropertyPosting updatedPropertyPosting = propertyPostServices.updateStatusProperties(postId, statusDto.getStatus());
	   LOGGER.info("Updation of status is executed successfully");
	   PropertyPostDto updatedPropertyPostDto = postMapper.mapToPropertyPostDto(updatedPropertyPosting);
	   return ResponseEntity.ok(updatedPropertyPostDto);
	}
		// fetch property details by id
				//http://localhost:8080/propertyhost/updatePayment/{postId}
				@Operation(
						summary="Update payment status by Id REST API",
						description="Update payment status By ID is used to update the payment status by id"
						)
				@ApiResponse(
						responseCode = "201",
						description = "HTTP STATUS 201 CREATED"
						)
	@PutMapping("updatePayment")
	public ResponseEntity<PropertyPostDto> updatePropertyPayment(PaymentDto paymentDto){
		PropertyPosting updatedPropertyPosting = propertyPostServices.updatePaymentStatusProperties(paymentDto.getPostId(), paymentDto.isPayment());
		LOGGER.info("Updation of payment us executed successfully");
		PropertyPostDto updatedPropertyPostDto = postMapper.mapToPropertyPostDto(updatedPropertyPosting);
		return ResponseEntity.ok(updatedPropertyPostDto);
	}
	
}
