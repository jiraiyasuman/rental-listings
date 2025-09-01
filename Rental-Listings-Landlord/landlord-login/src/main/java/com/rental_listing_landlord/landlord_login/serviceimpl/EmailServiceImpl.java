package com.rental_listing_landlord.landlord_login.serviceimpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.rental_listing_landlord.landlord_login.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService{

	private JavaMailSender javaMailSender;
	private final ExecutorService executor = Executors.newCachedThreadPool();
	@Autowired
	public EmailServiceImpl(JavaMailSender javaMailSender) {
		super();
		this.javaMailSender = javaMailSender;
	}
	@Override
	public void sendOtpEmailAsync(String to, String subject, String message) {
		executor.submit(() -> {
			
			try {
				SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
				simpleMailMessage.setTo(to);
				simpleMailMessage.setSubject(subject);
				simpleMailMessage.setText(message);
				javaMailSender.send(simpleMailMessage);
			}catch(Exception e) {
				e.printStackTrace();
			}
		});	
	}
}
