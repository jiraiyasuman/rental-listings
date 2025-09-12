package com.online_rental.tenant_login.serviceimpl;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.online_rental.tenant_login.entity.Login;
import com.online_rental.tenant_login.entity.OtpLogin;
import com.online_rental.tenant_login.repository.OtpRepository;
import com.online_rental.tenant_login.service.EmailService;
import com.online_rental.tenant_login.service.OtpService;

@Service
public class OtpServiceImpl  implements OtpService{

	private OtpRepository otpTokenRepository;
	private EmailService emailService;
	@Autowired
	public OtpServiceImpl(OtpRepository otpTokenRepository, EmailService emailService) {
		super();
		this.otpTokenRepository = otpTokenRepository;
		this.emailService = emailService;
	}
	
	private final Random random = new Random();
	@Value("${app.otp.valid.seconds}")
	private int resendCooldownSeconds;
	@Value("${app.otp.resend.cooldown.seconds}")
	private int otpValidSeconds;
	@Override
	public OtpLogin createAndSendOtp(Login user) {
		String code = String.format("%06d", random.nextInt(1000000));
		OtpLogin token = new OtpLogin();
		token.setUser(user);
		token.setCode(code);
		token.setExpiresAt(LocalDateTime.now().plusSeconds(otpValidSeconds));
		token.setResendAvailableAt(LocalDateTime.now().plusSeconds(resendCooldownSeconds));
		otpTokenRepository.save(token);
		String subject = "Otp for Login";
		String message = "Dear "+user.getName()+",<br>  Your otp for login is "+code+".<br> Team Property Rental";
		emailService.sendOtpEmailAsync(user.getEmail(), subject, message);
		return token;
	}
	@Override
	public boolean verifyOtp(Login user, String code) {
		var otp = otpTokenRepository.findByUserOrderByIdDesc(user);
		if(otp.isEmpty())
			return false;
OtpLogin token = otp.get();
		if(token.isUsed())
			return false;
		if(token.getExpiresAt().isBefore(LocalDateTime.now()))
				return false;
		if(!token.getCode().equals(code))
			return false;
		token.setUsed(true);
		otpTokenRepository.save(token);
		return true;
	}
	@Override
	public boolean canResend(Login user) {
		var opt =otpTokenRepository.findByUserOrderByIdDesc(user);
		if(opt.isEmpty())
			return true;
		OtpLogin last = opt.get();
		if(last.getResendAvailableAt()==null)
			return true;
		
		return last.getResendAvailableAt().isBefore(LocalDateTime.now());
	}
	@Override
	public OtpLogin resendOtp(Login user) {
		// TODO Auto-generated method stub
		return createAndSendOtp(user);
	}
		
}
