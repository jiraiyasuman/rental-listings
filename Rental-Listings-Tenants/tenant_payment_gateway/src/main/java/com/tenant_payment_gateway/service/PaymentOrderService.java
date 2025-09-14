package com.tenant_payment_gateway.service;

import java.util.Map;

import com.tenant_payment_gateway.entity.PaymentOrder;

public interface PaymentOrderService {

	PaymentOrder createOrder(PaymentOrder paymentOrder)throws Exception;
	
	PaymentOrder updateOrder(Map<String, String> responsePayLoad);
}
