package com.online_rental.tenant_login.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online_rental.tenant_login.entity.Login;
import com.online_rental.tenant_login.service.LoginService;
import com.online_rental.tenant_login.service.OtpService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/v1/tenant_login")
public class AuthController {

    private final LoginService userService;
    private final OtpService otpService;

    public AuthController(LoginService userService, OtpService otpService) {
        this.userService = userService;
        this.otpService = otpService;
    }

    
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
            return ResponseEntity.status(401).body(Map.of("error","invalid credentials"));
        }

        if (userService.isLocked(user)) {
            return ResponseEntity.status(423).body(Map.of("error","account locked until " + user.getLockLocalDateTime()));
        }

        boolean ok = userService.validatePasswordAndUpdateAttempts(user, password);
        if (!ok) {
            int remaining = Math.max(0, 5 - user.getFailedAttempts());
            return ResponseEntity.status(401).body(Map.of("error","invalid credentials", "remainingAttempts", remaining));
        }

        var token = otpService.createAndSendOtp(user);

        HttpSession session = req.getSession(true);
        session.setAttribute("OTP_PENDING_USER", user.getEmail());
        session.setAttribute("OTP_CREATED_AT", LocalDateTime.now());
        
        return ResponseEntity.ok(Map.of("message","otp_sent"));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String,String> body, HttpServletRequest req) {
        String code = body.get("code");
        HttpSession session = req.getSession(false);
        if (session == null) return ResponseEntity.status(401).body(Map.of("error","session not found"));

        String username = (String) session.getAttribute("OTP_PENDING_USER");
        if (username == null) return ResponseEntity.status(401).body(Map.of("error","no pending OTP"));

        Login user = userService.findByUserName(username);
        if (user == null) return ResponseEntity.status(401).body(Map.of("error","user not found"));

        boolean ok = otpService.verifyOtp(user, code);
        if (!ok) return ResponseEntity.status(401).body(Map.of("error","invalid or expired otp"));

        // OTP verified -> mark session as authenticated
        session.setAttribute("AUTHENTICATED_USER", user.getEmail());
        session.removeAttribute("OTP_PENDING_USER");
        return ResponseEntity.ok(Map.of("message","authenticated"));
    }

    @PostMapping("/resend-otp")
    public ResponseEntity<?> resendOtp(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session == null) return ResponseEntity.status(401).body(Map.of("error","session missing"));
        String username = (String) session.getAttribute("OTP_PENDING_USER");
        if (username == null) return ResponseEntity.status(400).body(Map.of("error","no pending OTP"));

        Login user = userService.findByUserName(username);
        if (user == null) return ResponseEntity.status(400).body(Map.of("error","user not found"));

        if (!otpService.canResend(user)) {
            return ResponseEntity.status(429).body(Map.of("error","resend cooldown active"));
        }

        otpService.resendOtp(user);
        return ResponseEntity.ok(Map.of("message","otp_resent"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok(Map.of("message","logged_out"));
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session == null) return ResponseEntity.status(401).body(Map.of("error","not authenticated"));
        String username = (String) session.getAttribute("AUTHENTICATED_USER");
        if (username == null) return ResponseEntity.status(401).body(Map.of("error","not authenticated"));
        return ResponseEntity.ok(Map.of("username", username));
    }
}
