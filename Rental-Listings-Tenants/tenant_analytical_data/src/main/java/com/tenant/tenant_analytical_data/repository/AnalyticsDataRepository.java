package com.tenant.tenant_analytical_data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tenant.tenant_analytical_data.entity.AnalyticsData;


public interface AnalyticsDataRepository extends JpaRepository<AnalyticsData, Long>{

}
