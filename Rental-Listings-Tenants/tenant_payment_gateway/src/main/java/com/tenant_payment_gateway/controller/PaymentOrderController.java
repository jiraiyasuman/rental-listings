package com.tenant_payment_gateway.controller;

import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenant_payment_gateway.entity.PaymentOrder;
import com.tenant_payment_gateway.entity.dto.PaymentOrderDto;
import com.tenant_payment_gateway.mapper.PaymentOrderMapper;
import com.tenant_payment_gateway.service.PaymentOrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
@CrossOrigin("")
@RestController
@RequestMapping("api/v1/tenant_payment")
@Tag(
		name="Tenant Payment REST API for tenant microservice components",
		description = "INSERT payment and Update payment REST API"
		)
public class PaymentOrderController {
	private PaymentOrderService paymentOrderService;
	private PaymentOrderMapper paymentOrderMapper = new PaymentOrderMapper();
	private Logger LOGGER = Logger.getLogger(getClass().getName());
	@Autowired
	public PaymentOrderController(PaymentOrderService paymentOrderService) {
		super();
		this.paymentOrderService = paymentOrderService;
	}
	// http://localhost:8090/api/v1/tenant_payment/create
	@Operation(
			summary = "Create Payment REST API",
			description = "Create Payment REST API is used to save the details of the payments made"
			)
	@ApiResponse(
			responseCode = "201",
			description = "HTTP STATUS 201 created"
			)
	@PostMapping(value="create",produces = "application/json")
	public ResponseEntity<PaymentOrder> createOrder(@RequestBody @Valid PaymentOrderDto paymentOrderDto){
		PaymentOrder paymentOrder = paymentOrderMapper.mapToPaymentOrder(paymentOrderDto);
		PaymentOrder savedOrder = null;
		try {
			savedOrder = paymentOrderService.createOrder(paymentOrder);
		LOGGER.info("Payment controller for order creation is being executed successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(savedOrder);
	}
	// http://localhost:8090/api/v1/tenant_payment/handle
		@Operation(
				summary = "Update Payment REST API",
				description = "Update Payment REST API is used to update the details of the payments made"
				)
		@ApiResponse(
				responseCode = "201",
				description = "HTTP STATUS 201 created"
				)
	@PutMapping("/handle")
    public ResponseEntity<String> handlePaymentCallBack(@RequestBody Map<String, String> respPayLoad) {
        System.out.println("Payment Callback JSON: " + respPayLoad);

        try {
            paymentOrderService.updateOrder(respPayLoad);
            LOGGER.info("Payment Order updation successfully executed");
            return ResponseEntity.ok("success"); 
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warning("Payment Order updation is not successfully executed");
            return ResponseEntity.status(500).body("failure"); 
        }
    }
}
