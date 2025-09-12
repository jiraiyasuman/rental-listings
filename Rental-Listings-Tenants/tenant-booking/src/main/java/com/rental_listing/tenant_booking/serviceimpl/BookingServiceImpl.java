package com.rental_listing.tenant_booking.serviceimpl;

import java.awt.print.Book;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rental_listing.tenant_booking.entity.Booking;
import com.rental_listing.tenant_booking.exception.NotFoundException;
import com.rental_listing.tenant_booking.repository.BookingRepository;
import com.rental_listing.tenant_booking.service.BookingService;
@Service
public class BookingServiceImpl implements BookingService{

	private BookingRepository bookingRepository;
	private Logger LOGGER = Logger.getLogger(getClass().getName());
	private JavaMailSender javaMailSender;
	@Autowired
	public BookingServiceImpl(BookingRepository bookingRepository) {
		super();
		this.bookingRepository = bookingRepository;
	}

	@Autowired
	@Override
	@Transactional
	public Booking addBookingDetails(Booking booking) {
		Booking savedBooking = bookingRepository.save(booking);
		LOGGER.info("Booking save details is being executed successfully");
		String to = booking.getEmail();
		String subject = "Confirmation of Booking";
		String message = "Dear "+booking.getName()+",<br> This is to inform you that your booking is successfully registered with the following booking number for your future reference"+booking.getBookingId();
		sendEmail(to, subject, message);
		return savedBooking;
	}

	public void sendEmail(String to,String subject,String message) {
		try {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setTo(to);
			simpleMailMessage.setSubject(subject);
			simpleMailMessage.setText(message);
			javaMailSender.send(simpleMailMessage);
			LOGGER.info("Sending Email is executed successfully");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<Booking> getAllDetails() {
		List<Booking> getAllDetail = bookingRepository.findAll();
		LOGGER.info("Booking get ALL details is being successfully executed");
		return getAllDetail;
	}

	@Override
	@Transactional
	public Booking updateBookingDetails(int id,Booking booking) {
		Booking getById = bookingRepository.findById(id).get();
		if(getById==null) {
			LOGGER.warning("No such records exist");
			throw new NotFoundException("No such details exist by that id");
		}
		getById.setMobile(booking.getMobile());
		getById.setAddress(booking.getAddress());
		getById.setAgeMonths(booking.getAgeMonths());
		getById.setAgeYears(booking.getAgeYears());
		getById.setBookingId(booking.getBookingId());
		getById.setMobile(booking.getMobile());
		getById.setName(booking.getName());
		getById.setName(booking.getName());
		getById.setOwnerName(booking.getOwnerName());
		getById.setPropertyCategory(booking.getPropertyCategory());
		getById.setPropertyClassification(booking.getPropertyClassification());
		getById.setPropertyName(booking.getPropertyName());
		getById.setPropertyType(booking.getPropertyType());
		Booking updatedBooking = bookingRepository.save(getById);
		LOGGER.info("Update Booking is being executed");
		return updatedBooking;
	}

	@Override
	public List<Booking> getDetailsByEmail(String email) {
		List<Booking> getByEmail = bookingRepository.getDetailsByEmail(email);
		if(getByEmail == null) {
			LOGGER.warning("No such records exist");
			throw new NotFoundException("No such details exist by that id");
		}
		LOGGER.info("Booking get by email is being executed successfully");
		return getByEmail;
	}

	@Override
	public Booking getDetailsById(int id) {
		Booking getById = bookingRepository.findById(id).get();
		if(getById==null) {
			LOGGER.warning("No such records exist");
			throw new NotFoundException("No such details exist by that id");
		}
		LOGGER.info("Booking details by id is being executed successfully");
		return getById;
	}

	@Override
	@Transactional
	public void deleteDetailsById(int id) {
		Booking getById = bookingRepository.findById(id).get();
		if(getById==null) {
			LOGGER.warning("No such records exist");
			throw new NotFoundException("No such details exist by that id");
		}
		bookingRepository.deleteById(id);
		LOGGER.info("Delete records by id is being executed successfully");
	}

	@Override
	public Booking getDetailsByBookingId(String id) {
		Booking getByBookingId = bookingRepository.getDetailsByBookingId(id);
		if(getByBookingId==null) {
			LOGGER.warning("No such records exist");
			throw new NotFoundException("No such details exist by that id");
		}
		LOGGER.info("Get by Booking ID is being successfully executed");
		return getByBookingId;
	}

}
