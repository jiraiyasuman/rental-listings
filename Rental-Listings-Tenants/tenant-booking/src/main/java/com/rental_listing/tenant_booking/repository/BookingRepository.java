package com.rental_listing.tenant_booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rental_listing.tenant_booking.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer>{

	public List<Booking> getDetailsByEmail(String email);
	public Booking getDetailsByBookingId(String bookingId);
}
