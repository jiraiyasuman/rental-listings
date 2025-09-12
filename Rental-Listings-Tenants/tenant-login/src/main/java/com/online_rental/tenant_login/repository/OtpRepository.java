package com.online_rental.tenant_login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online_rental.tenant_login.entity.Login;
import com.online_rental.tenant_login.entity.OtpLogin;

public interface OtpRepository extends JpaRepository<OtpLogin, Integer>{

	Optional<OtpLogin> findByUserOrderByIdDesc(Login user);
}
