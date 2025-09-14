package com.online_rental.tenant_login.serviceimpl;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.online_rental.tenant_login.entity.Login;
import com.online_rental.tenant_login.repository.LoginRepository;
import com.online_rental.tenant_login.service.LoginService;
@Service
public class UserServiceImpl implements LoginService{

	@Value("${app.login.maxFailedAttempts}")
    private int maxFailedAttempts;

    @Value("${app.account.lock.hours}")
    private int lockHours;
	@Override
	public Login registerIfNotExists(Login login) {
		
		return userRepository.findByEmail(login.getEmail()).orElseGet(() -> {
			 Login u = new Login();
		        u.setEmail(login.getEmail());
		        u.setPassword(login.getPassword());
		        u.setName(login.getName());
		        return userRepository.save(login);
		});
	}

	private LoginRepository userRepository;
	private PasswordEncoder passwordEncoder;
	@Autowired
	public UserServiceImpl(LoginRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public boolean isLocked(Login user) {
		// TODO Auto-generated method stub
		 return user.getLockLocalDateTime() != null && user.getLockLocalDateTime().isAfter(LocalDateTime.now());
	}

	@Override
	public boolean validatePasswordAndUpdateAttempts(Login user, String password) {
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
	public Login findByUserName(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email).orElse(null);
	}

}
