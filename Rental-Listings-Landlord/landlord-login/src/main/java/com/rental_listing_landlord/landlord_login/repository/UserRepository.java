package com.rental_listing_landlord.landlord_login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rental_listing_landlord.landlord_login.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email);
}
