package com.rental_listing_landlord.analytical_data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rental_listing_landlord.analytical_data.entity.AnalyticsData;

public interface AnalyticsDataRepository extends JpaRepository<AnalyticsData, Long>{

}
