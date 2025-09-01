package com.rental_listing_landlord.landlord_login.serviceimpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rental_listing_landlord.landlord_login.entity.User;
import com.rental_listing_landlord.landlord_login.repository.UserRepository;
import com.rental_listing_landlord.landlord_login.service.UserService;
@Service
public class UserServiceImpl implements UserService{

	@Value("${app.login.maxFailedAttempts}")
    private int maxFailedAttempts;

    @Value("${app.account.lock.hours}")
    private int lockHours;
	@Override
	public User registerIfNotExists(String email, String password, String name) {
		
		return userRepository.findByEmail(email).orElseGet(() -> {
			 User u = new User();
		        u.setEmail(email);
		        u.setPassword(passwordEncoder.encode(password));
		        u.setName(name);
		        return userRepository.save(u);
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
