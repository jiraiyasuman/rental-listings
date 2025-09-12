package com.rental_listing.tenant_booking.service;

import java.util.List;

import com.rental_listing.tenant_booking.entity.Booking;

public interface BookingService {

	
	public Booking addBookingDetails(Booking booking);
	
	public List<Booking> getAllDetails();
	
	public Booking updateBookingDetails(int id,Booking booking);
	
	public List<Booking> getDetailsByEmail(String email);
	
	public Booking getDetailsByBookingId(String id);
	
	public Booking getDetailsById(int id);
	
	public void deleteDetailsById(int id);
}
