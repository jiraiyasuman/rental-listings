package com.tenant_payment_gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tenant_payment_gateway.entity.PaymentOrder;

public interface PaymentOrderRepository  extends JpaRepository<PaymentOrder, Integer>{

	PaymentOrder findByRazorPayOrderId(String rayzorpayOrderId);
	
}
