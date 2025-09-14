package com.rental_listing_landlord.landlord_login.serviceimpl;

import java.time.LocalDateTime;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rental_listing_landlord.landlord_login.entity.OtpToken;
import com.rental_listing_landlord.landlord_login.entity.User;
import com.rental_listing_landlord.landlord_login.repository.OtpTokenRepository;
import com.rental_listing_landlord.landlord_login.service.EmailService;
import com.rental_listing_landlord.landlord_login.service.OtpService;

import io.github.resilience4j.retry.annotation.Retry;

@Service
public class OtpServiceImpl  implements OtpService{

	private OtpTokenRepository otpTokenRepository;
	private EmailService emailService;
	@Autowired
	public OtpServiceImpl(OtpTokenRepository otpTokenRepository, EmailService emailService) {
		super();
		this.otpTokenRepository = otpTokenRepository;
		this.emailService = emailService;
	}
	 private static final Logger LOGGER = LoggerFactory.getLogger(OtpServiceImpl.class);

	
	private final Random random = new Random();
	@Value("${app.otp.valid.seconds}")
	private int resendCooldownSeconds;
	@Value("${app.otp.resend.cooldown.seconds}")
	private int otpValidSeconds;
	@Override
	@Transactional
	@Retry(
			name="${spring.application.name}",
			fallbackMethod = ""
			)
	public OtpToken createAndSendOtp(User user) {
		String code = String.format("%06d", random.nextInt(1000000));
		OtpToken token = new OtpToken();
		token.setUser(user);
		token.setCode(code);
		token.setExpiresAt(LocalDateTime.now().plusSeconds(otpValidSeconds));
		token.setResendAvailableAt(LocalDateTime.now().plusSeconds(resendCooldownSeconds));
		otpTokenRepository.save(token);
		String subject = "Otp for Login";
		String message = "Dear "+user.getName()+",<br>  Your otp for login is "+code+".<br> Team Property Rental";
		emailService.sendOtpEmailAsync(user.getEmail(), subject, message);
		LOGGER.info("Otp creation service component is being executed successfully");
		return token;
	}
	@Override
	@Retry(
			name="${spring.application.name}",
			fallbackMethod = ""
			)
	public boolean verifyOtp(User user, String code) {
		var otp = otpTokenRepository.findByUserOrderByIdDesc(user);
		if(otp.isEmpty())
			return false;
		OtpToken token = otp.get();
		if(token.isUsed())
			return false;
		if(token.getExpiresAt().isBefore(LocalDateTime.now()))
				return false;
		if(!token.getCode().equals(code))
			return false;
		token.setUsed(true);
		otpTokenRepository.save(token);
		LOGGER.info("Otp verification service component is being executed successfully");
		return true;
	}
	@Override
	@Retry(
			name="${spring.application.name}",
			fallbackMethod = ""
			)
	public boolean canResend(User user) {
		var opt =otpTokenRepository.findByUserOrderByIdDesc(user);
		if(opt.isEmpty())
			return true;
		OtpToken last = opt.get();
		if(last.getResendAvailableAt()==null)
			return true;
		LOGGER.info("Otp resend checking service component is being executed successfully");
		return last.getResendAvailableAt().isBefore(LocalDateTime.now());
	}
	@Override
	@Retry(
			name="${spring.application.name}",
			fallbackMethod = ""
			)
	public OtpToken resendOtp(User user) {
		return createAndSendOtp(user);
	}
		
}
