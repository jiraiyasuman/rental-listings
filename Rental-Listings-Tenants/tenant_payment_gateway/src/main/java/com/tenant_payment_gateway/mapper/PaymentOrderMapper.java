package com.tenant_payment_gateway.mapper;

import com.tenant_payment_gateway.entity.PaymentOrder;
import com.tenant_payment_gateway.entity.dto.PaymentOrderDto;

public class PaymentOrderMapper {

	
	public PaymentOrder mapToPaymentOrder(PaymentOrderDto paymentOrderDto) {
		PaymentOrder paymentOrder = new PaymentOrder();
		paymentOrder.setAmount(paymentOrderDto.getAmount());
		paymentOrder.setBookingId(paymentOrderDto.getBookingId());
		paymentOrder.setEmail(paymentOrderDto.getEmail());
		paymentOrder.setMobile(paymentOrderDto.getMobile());
		paymentOrder.setName(paymentOrderDto.getName());
		paymentOrder.setPropertyName(paymentOrderDto.getPropertyName());
		return paymentOrder;
	}
	
	public PaymentOrderDto mapToPaymentOrder(PaymentOrder paymentOrder) {
		PaymentOrderDto paymentOrderDto = new PaymentOrderDto();
		paymentOrderDto.setAmount(paymentOrder.getAmount());
		paymentOrderDto.setBookingId(paymentOrder.getBookingId());
		paymentOrderDto.setEmail(paymentOrder.getEmail());
		paymentOrderDto.setMobile(paymentOrder.getMobile());
		paymentOrderDto.setName(paymentOrder.getName());
		paymentOrderDto.setPropertyName(paymentOrder.getPropertyName());
		return paymentOrderDto;
	}
}
