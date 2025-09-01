package com.rental_listing_landlord.landlord_login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rental_listing_landlord.landlord_login.entity.OtpToken;
import com.rental_listing_landlord.landlord_login.entity.User;

public interface OtpTokenRepository extends JpaRepository<OtpToken, Integer>{

	Optional<OtpToken> findByUserOrderByIdDesc(User user);
}
