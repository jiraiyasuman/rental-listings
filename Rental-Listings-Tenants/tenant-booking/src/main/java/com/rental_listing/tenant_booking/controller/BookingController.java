package com.rental_listing.tenant_booking.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rental_listing.tenant_booking.dto.BookingDto;
import com.rental_listing.tenant_booking.dto.BookingEmail;
import com.rental_listing.tenant_booking.dto.BookingIdDto;
import com.rental_listing.tenant_booking.entity.Booking;
import com.rental_listing.tenant_booking.mapper.BookingMapper;
import com.rental_listing.tenant_booking.service.BookingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/tenant")
@Tag(
		name ="CRUD Rest API for Tenant booking Resource",
		description = "Create Tenant booking, Update Tenant booking, Get Tenant booking By Id, Get All Tenant booking, Delete Tenant booking by id"
		)
@CrossOrigin("*")
public class BookingController {

	private BookingService bookingService;

	BookingMapper bookingMapper = new BookingMapper();
	private Logger LOGGER = Logger.getLogger(getClass().getName());
	@Autowired
	public BookingController(BookingService bookingService) {
		super();
		this.bookingService = bookingService;
	}
	@Operation(
			summary ="Create Tenant booking REST API",
			description = "Create Tenant booking REST API is used to save tenant booking details in a database"
			)
	@ApiResponse(
			responseCode = "201",
			description = "HTTP STATUS 201 created"
			)
	@PostMapping("add")
	public ResponseEntity<Booking> addBooking(@Valid @RequestBody BookingDto bookingDto){
		Booking booking = bookingMapper.mapToBooking(bookingDto);
		Booking savedBooking= bookingService.addBookingDetails(booking);
		LOGGER.info("Saved Component is being executed successfully");
		return ResponseEntity.ok(savedBooking);
	}
	@Operation(
			summary ="Get ALL Tenant booking REST API",
			description = "GET ALL Tenant booking REST API is used to fetch all tenant booking details in a database"
			)
	@ApiResponse(
			responseCode = "201",
			description = "HTTP STATUS 201 created"
			)
	@GetMapping("list")
	public ResponseEntity<List<Booking>> getAllList(){
		List<Booking> getAll = bookingService.getAllDetails();
		LOGGER.info("Get All List is being executed successfully");
		return ResponseEntity.ok(getAll);
	}
	@Operation(
			summary ="get Tenant by id booking REST API",
			description = "Get Tenant booking By id REST API is used to fetch tenant booking details by id in a database"
			)
	@ApiResponse(
			responseCode = "201",
			description = "HTTP STATUS 201 created"
			)
	@GetMapping("searchByBookingId")
	public ResponseEntity<Booking> getByBookingId(@Valid @RequestBody BookingIdDto bookingIdDto){
		Booking booking = bookingService.getDetailsByBookingId(bookingIdDto.getBookingId());
		LOGGER.info("Search by booking id is being executed successfully");
		return ResponseEntity.ok(booking);
	}
	@Operation(
			summary ="Update Tenant by id booking REST API",
			description = "Update Tenant booking By id REST API is used to update tenant booking details by id in a database"
			)
	@ApiResponse(
			responseCode = "201",
			description = "HTTP STATUS 201 created"
			)
	@PutMapping("update/{id}")
	public ResponseEntity<Booking> updateBooking(@PathVariable("id") int id,@Valid @RequestBody BookingDto bookingDto ){
		Booking booking = bookingMapper.mapToBooking(bookingDto);
		Booking updateBooking = bookingService.updateBookingDetails(id,booking);
		LOGGER.info("Update book by id is being successfully executed");
		return ResponseEntity.ok(updateBooking);
	}
	@Operation(
			summary ="get Tenant by id booking REST API",
			description = "Get Tenant booking By id REST API is used to fetch tenant booking details by id in a database"
			)
	@ApiResponse(
			responseCode = "201",
			description = "HTTP STATUS 201 created"
			)
	@GetMapping("list/{id}")
	public ResponseEntity<Booking> getBookingById(@PathVariable("id")int id){
		Booking booking = bookingService.getDetailsById(id);
		LOGGER.info("Get By details by id is being successfull");
		return ResponseEntity.ok(booking);
	}
	@Operation(
			summary ="get Tenant by email booking REST API",
			description = "Get Tenant booking By email REST API is used to fetch tenant booking details by id in a database"
			)
	@ApiResponse(
			responseCode = "201",
			description = "HTTP STATUS 201 created"
			)
	
	@GetMapping("searchByEmail")
	public ResponseEntity<List<Booking>> getBookingByEmail(@Valid @RequestBody BookingEmail bookingEmail){
		List<Booking> getByEmail = bookingService.getDetailsByEmail(bookingEmail.getEmail());
		LOGGER.info("Get by emails is being executed sucessfully");
		return ResponseEntity.ok(getByEmail);
	}
	@Operation(
			summary ="delete Tenant by id booking REST API",
			description = "delete Tenant booking By id REST API is used to fetch tenant booking details by id in a database"
			)
	@ApiResponse(
			responseCode = "201",
			description = "HTTP STATUS 201 created"
			)
	@GetMapping("delete/{id}")
	public ResponseEntity<String> deleteById( @PathVariable("id") int id){
		bookingService.deleteDetailsById(id);
		LOGGER.info("Delete By id is being executed successfully");
		return ResponseEntity.ok("Deleted successfully");
	}
}
