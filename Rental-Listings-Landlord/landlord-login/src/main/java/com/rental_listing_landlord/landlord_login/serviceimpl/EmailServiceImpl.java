package com.rental_listing_landlord.landlord_login.serviceimpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.rental_listing_landlord.landlord_login.service.EmailService;

import io.github.resilience4j.retry.annotation.Retry;

@Service
public class EmailServiceImpl implements EmailService{

	private JavaMailSender javaMailSender;
	private final ExecutorService executor = Executors.newCachedThreadPool();
	@Autowired
	public EmailServiceImpl(JavaMailSender javaMailSender) {
		super();
		this.javaMailSender = javaMailSender;
	}
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Override
	@Retry(
			name="${spring.application.name}",
			fallbackMethod = ""
			)
	public void sendOtpEmailAsync(String to, String subject, String message) {
		executor.submit(() -> {
			
			try {
				SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
				simpleMailMessage.setTo(to);
				simpleMailMessage.setSubject(subject);
				simpleMailMessage.setText(message);
				javaMailSender.send(simpleMailMessage);
				LOGGER.info("Email section is being successfully executed");
			}catch(Exception e) {
				e.printStackTrace();
			}
		});	
	}
}
