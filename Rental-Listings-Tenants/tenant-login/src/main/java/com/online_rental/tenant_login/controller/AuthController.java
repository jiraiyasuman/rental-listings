package com.online_rental.tenant_login.controller;

import java.time.LocalDateTime;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online_rental.tenant_login.entity.Login;
import com.online_rental.tenant_login.service.LoginService;
import com.online_rental.tenant_login.service.OtpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/tenant_login")
public class AuthController {

	private final LoginService userService;
    private final OtpService otpService;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    public AuthController(LoginService userService, OtpService otpService) {
        this.userService = userService;
        this.otpService = otpService;
    }
    // http:localhost:8087/api/v1/tenant_login/login
    @Operation(
    		summary = "Login logic in tenant login authcontroller",
    		description = "Login logic in tenant login"
    		)
    @ApiResponse(
    		responseCode = "201",
    		description = "HTTP STATUS 201 created"
    		)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> body, HttpServletRequest req) {
        String username = body.get("username");
        String password = body.get("password");
        if (username == null || password == null) {
            return ResponseEntity.badRequest().body(Map.of("error","username and password required"));
        }

        Login user = userService.findByUserName(username);
        if (user == null) {
            // avoid leaking existence - mimic failed attempt path
        	LOGGER.error("Invalid Credentials in tenant login controller");
            return ResponseEntity.status(401).body(Map.of("error","invalid credentials"));
        }

        if (userService.isLocked(user)) {
        	LOGGER.error("Account is locked due to excessive incorrect tries in tenant login controller");
            return ResponseEntity.status(423).body(Map.of("error","account locked until " + user.getLockLocalDateTime()));
        }

        boolean ok = userService.validatePasswordAndUpdateAttempts(user, password);
        if (!ok) {
            int remaining = Math.max(0, 5 - user.getFailedAttempts());
            LOGGER.error("Invalid Credentials in tenant login controller");
            return ResponseEntity.status(401).body(Map.of("error","invalid credentials", "remainingAttempts", remaining));
        }

        var token = otpService.createAndSendOtp(user);

        HttpSession session = req.getSession(true);
        session.setAttribute("OTP_PENDING_USER", user.getEmail());
        session.setAttribute("OTP_CREATED_AT", LocalDateTime.now());
        LOGGER.info("Tenant Login component is being successfuly executed");
        return ResponseEntity.ok(Map.of("message","otp_sent"));
    }
    @Operation(
    		summary = "Verify otp logic in tenant login authcontroller",
    		description = "Verify otp logic in tenant login"
    		)
    @ApiResponse(
    		responseCode = "201",
    		description = "HTTP STATUS 201 created"
    		)
 // http:localhost:8087/api/v1/tenant_login/verify-otp
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String,String> body, HttpServletRequest req) {
        String code = body.get("code");
        HttpSession session = req.getSession(false);
        if (session == null) { 
           LOGGER.error("Error! Session not found");
        	return ResponseEntity.status(401).body(Map.of("error","session not found"));
        	
        }
        String username = (String) session.getAttribute("OTP_PENDING_USER");
        if (username == null) {
        	LOGGER.error("Error! No pending user is found!");
        	return ResponseEntity.status(401).body(Map.of("error","no pending OTP"));
        	}
        Login user = userService.findByUserName(username);
        if (user == null) {
        	LOGGER.error("Error! Username does not exist");
        	return ResponseEntity.status(401).body(Map.of("error","user not found"));
        }

        boolean ok = otpService.verifyOtp(user, code);
        if (!ok) {
        	LOGGER.error("Error! Invalid or expired OTP");
        	return ResponseEntity.status(401).body(Map.of("error","invalid or expired otp"));
        }
        	
        // OTP verified -> mark session as authenticated
        session.setAttribute("AUTHENTICATED_USER", user.getEmail());
        session.removeAttribute("OTP_PENDING_USER");
        LOGGER.info("tenant Login Otp verification is being successfully executed");
        return ResponseEntity.ok(Map.of("message","authenticated"));
    }
    @Operation(
    		summary = "Resend Otp logic in tenant login authcontroller",
    		description = "Resend Otp logic in tenant login"
    		)
    @ApiResponse(
    		responseCode = "201",
    		description = "HTTP STATUS 201 created"
    		)
 // http:localhost:8087/api/v1/tenant_login/resend-otp
    @PostMapping("/resend-otp")
    public ResponseEntity<?> resendOtp(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session == null) { 
        	LOGGER.error("Error! Session is missing");
        	return ResponseEntity.status(401).body(Map.of("error","session missing"));
        	}
        String username = (String) session.getAttribute("OTP_PENDING_USER");
        if (username == null) {
        	LOGGER.error("Error! No pending Otp");
        	return ResponseEntity.status(400).body(Map.of("error","no pending OTP"));
        }
        Login user = userService.findByUserName(username);
        if (user == null) { 
        	LOGGER.error("ERROR! User not found");
        	return ResponseEntity.status(400).body(Map.of("error","user not found"));
        }
        if (!otpService.canResend(user)) {
        	LOGGER.error("Error! Resend cooldown is active");
            return ResponseEntity.status(429).body(Map.of("error","resend cooldown active"));
        }

        otpService.resendOtp(user);
        LOGGER.info("Otp resend component in controller class is being executed sucessfully");
        return ResponseEntity.ok(Map.of("message","otp_resent"));
    }
    @Operation(
    		summary = "Logout logic in tenant login authcontroller",
    		description = "Logout logic in tenant login"
    		)
    @ApiResponse(
    		responseCode = "201",
    		description = "HTTP STATUS 201 created"
    		)
 // http:localhost:8087/api/v1/tenant_login/logout
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        LOGGER.info("Logout section of controller is being successfully executed");
        return ResponseEntity.ok(Map.of("message","logged_out"));
    }
    @Operation(
    		summary = "Sucessful login logic in tenant login authcontroller",
    		description = "successful login logic in tenant login"
    		)
    @ApiResponse(
    		responseCode = "201",
    		description = "HTTP STATUS 201 created"
    		)
 // http:localhost:8087/api/v1/tenant_login/dashboard
    @GetMapping("/dashboard")
    public ResponseEntity<?> me(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session == null) { 
        	LOGGER.info("Error! Not authenticated");
        	return ResponseEntity.status(401).body(Map.of("error","not authenticated"));
        	}
        String username = (String) session.getAttribute("AUTHENTICATED_USER");
        if (username == null) { 
        	LOGGER.info("Error! Not authenticated");
        	return ResponseEntity.status(401).body(Map.of("error","not authenticated"));
        }
        LOGGER.info("Dashboard section of auth controller is being executed successfully");
        return ResponseEntity.ok(Map.of("username", username));
    }
}
