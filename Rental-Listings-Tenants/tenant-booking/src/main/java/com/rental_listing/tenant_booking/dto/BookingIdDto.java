package com.rental_listing.tenant_booking.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public class BookingIdDto {

	@Schema(name="Booking Id")
	@NotEmpty(message="Booking Id cannot be null/empty/blank")
	private String bookingId;

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public BookingIdDto(String bookingId) {
		super();
		this.bookingId = bookingId;
	}

	public BookingIdDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "BookingIdDto [bookingId=" + bookingId + "]";
	}
	
}
