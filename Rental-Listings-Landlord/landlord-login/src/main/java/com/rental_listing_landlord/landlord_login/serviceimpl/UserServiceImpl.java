package com.rental_listing_landlord.landlord_login.serviceimpl;

import java.time.LocalDateTime;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rental_listing_landlord.landlord_login.entity.User;
import com.rental_listing_landlord.landlord_login.repository.UserRepository;
import com.rental_listing_landlord.landlord_login.service.UserService;

import io.github.resilience4j.retry.annotation.Retry;
@Service
public class UserServiceImpl implements UserService{

	@Value("${app.login.maxFailedAttempts}")
    private int maxFailedAttempts;

    @Value("${app.account.lock.hours}")
    private int lockHours;
	@Override
	@Transactional
	@Retry(name="${spring.application.name}",fallbackMethod = "")
	public User registerIfNotExists(User login) {
		
		return userRepository.findByEmail(login.getEmail()).orElseGet(() -> {
			User u = new User();
		        u.setEmail(login.getEmail());
		        u.setPassword(login.getPassword());
		        u.setName(login.getName());
		        return userRepository.save(login);
		});
	}

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public boolean isLocked(User user) {
		// TODO Auto-generated method stub
		 return user.getLockLocalDateTime() != null && user.getLockLocalDateTime().isAfter(LocalDateTime.now());
	}

	@Override
	public boolean validatePasswordAndUpdateAttempts(User user, String password) {
		if (isLocked(user)) return false;
        boolean matches = passwordEncoder.matches(password, user.getPassword());
        if (matches) {
            user.setFailedAttempts(0);
        } else {
            user.setFailedAttempts(user.getFailedAttempts() + 1);
            if (user.getFailedAttempts() >= maxFailedAttempts) {
                user.setLockLocalDateTime(LocalDateTime.now().plusHours(lockHours));
            }
        }
        userRepository.save(user);
        return matches;
	}

	@Override
	public User findByUserName(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email).orElse(null);
	}

}
