package com.rental_listing.tenant_booking.mapper;

import com.rental_listing.tenant_booking.dto.BookingDto;
import com.rental_listing.tenant_booking.entity.Booking;

public class BookingMapper {

	public Booking mapToBooking(BookingDto bookingDto) {
		Booking booking = new Booking();
		booking.setMobile(bookingDto.getMobile());
		booking.setAddress(bookingDto.getAddress());
		booking.setAgeMonths(bookingDto.getAgeMonths());
		booking.setAgeYears(bookingDto.getAgeYears());
		booking.setMobile(bookingDto.getMobile());
		booking.setName(bookingDto.getName());
		booking.setName(bookingDto.getName());
		booking.setOwnerName(bookingDto.getOwnerName());
		booking.setPropertyCategory(bookingDto.getPropertyCategory());
		booking.setPropertyClassification(bookingDto.getPropertyClassification());
		booking.setPropertyName(bookingDto.getPropertyName());
		booking.setPropertyType(bookingDto.getPropertyType());
		return booking;
	}
	
	public BookingDto mapToBookingDto(Booking booking) {
		BookingDto bookingDto = new BookingDto();
		bookingDto.setMobile(booking.getMobile());
		bookingDto.setAddress(booking.getAddress());
		bookingDto.setAgeMonths(booking.getAgeMonths());
		bookingDto.setAgeYears(booking.getAgeYears());
		bookingDto.setMobile(booking.getMobile());
		bookingDto.setName(booking.getName());
		bookingDto.setName(booking.getName());
		bookingDto.setOwnerName(booking.getOwnerName());
		bookingDto.setPropertyCategory(booking.getPropertyCategory());
		bookingDto.setPropertyClassification(booking.getPropertyClassification());
		bookingDto.setPropertyName(booking.getPropertyName());
		bookingDto.setPropertyType(booking.getPropertyType());
		return bookingDto;
	}
	
	
}
