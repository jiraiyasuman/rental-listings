package com.tenant_payment_gateway.exception;

import lombok.Data;

@Data
public class ErrorResponse {

	private int status;
	private long timestamp;
	private String message;
}
