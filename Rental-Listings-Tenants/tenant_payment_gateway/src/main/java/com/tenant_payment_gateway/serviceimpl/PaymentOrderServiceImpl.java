package com.tenant_payment_gateway.serviceimpl;

import java.util.Map;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.tenant_payment_gateway.entity.PaymentOrder;
import com.tenant_payment_gateway.exception.NotFoundException;
import com.tenant_payment_gateway.repository.PaymentOrderRepository;
import com.tenant_payment_gateway.service.PaymentOrderService;

import io.github.resilience4j.retry.annotation.Retry;
@Service
public class PaymentOrderServiceImpl implements PaymentOrderService{

	private PaymentOrderRepository paymentOrderRepository;
	@Value("${razorpay.key.id}")
	private String razorpayKeyId;
	@Value("${razorpay.secret.key}")
	private String razorPaySecret;
	private Logger LOGGER = Logger.getLogger(getClass().getName());
	private RazorpayClient razorpayClient;
	
	@Autowired
	public PaymentOrderServiceImpl(PaymentOrderRepository paymentOrderRepository) {
		super();
		this.paymentOrderRepository = paymentOrderRepository;
	}

	@Override
	@Transactional
	@Retry(
			name="${spring.application.name}",
			fallbackMethod = ""
			)
	public PaymentOrder createOrder(PaymentOrder paymentOrder) throws Exception {
		JSONObject orderReq = new JSONObject();
		orderReq.put("amount", paymentOrder.getAmount()*100);
		orderReq.put("currency", "INR");
		orderReq.put("receipt", paymentOrder.getEmail());
		this.razorpayClient = new RazorpayClient(razorpayKeyId, razorPaySecret);
		Order razorPayOrder = razorpayClient.orders.create(orderReq);
		System.out.println(razorPayOrder);
		paymentOrder.setRayzorpayOrderId(razorPayOrder.get("id"));
		paymentOrder.setOrderStatus("PAYMENT_CREATED");
		paymentOrderRepository.save(paymentOrder);
		LOGGER.info("Create Payment Order is being successfully exceuted");
		return paymentOrder;
	}

	@Override
	@Retry(
			name="${spring.application.name}",
			fallbackMethod = ""
			)
	@Transactional
	public PaymentOrder updateOrder(Map<String, String> responsePayLoad) {
		String razorPayOrId = responsePayLoad.get("razorpay_order_id");
		PaymentOrder paymentOrder = paymentOrderRepository.findByRazorPayOrderId(razorPayOrId);
		if(paymentOrder == null) {
			LOGGER.warning("No such id exists");
			throw new NotFoundException("No such order id exists");
		}
		paymentOrder.setOrderStatus("PAYMENT_SUCCESSFUL");
		PaymentOrder updateOrder=paymentOrderRepository.save(paymentOrder);
		LOGGER.info("Update Payment Order is being successfully executed");
		return updateOrder;
	}

}
