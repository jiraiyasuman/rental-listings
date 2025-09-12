package com.online_rental.tenant_login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online_rental.tenant_login.entity.Login;

public interface LoginRepository extends JpaRepository<Login, Integer>{

	Optional<Login> findByEmail(String email);
}
