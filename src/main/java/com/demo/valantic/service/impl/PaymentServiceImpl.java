package com.demo.valantic.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.valantic.entity.Payment;
import com.demo.valantic.model.PaymentRequest;
import com.demo.valantic.repository.PaymentRepository;
import com.demo.valantic.service.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService { 
	    
	    private PaymentRepository paymentRepository;

	    public PaymentServiceImpl(PaymentRepository paymentRepository, @Value("${stripe.api.secretKey}") String secretKey) {
	        this.paymentRepository = paymentRepository;
	        Stripe.apiKey = secretKey;
	    }

	    public PaymentIntent createPaymentIntent(PaymentRequest paymentInfoRequest) throws StripeException {
	        List<String> paymentMethodTypes = new ArrayList<>();
	        paymentMethodTypes.add("card");

	        Map<String, Object> params = new HashMap<>();
	        params.put("amount", paymentInfoRequest.getAmount());
	        params.put("currency", paymentInfoRequest.getCurrency());
	        params.put("payment_method_types", paymentMethodTypes);

	        return PaymentIntent.create(params);
	    }

	    public ResponseEntity<String> stripePayment(PaymentRequest request) throws Exception {
	    	 System.out.println("request email address.." + request.getAmount());
	        Payment payment = paymentRepository.findByUserEmail(request.getEmail());
	        System.out.println("request email address.." + request.getEmail());

	        if (payment == null) {
	        	savePaymentUser(request);
	           
	        }
	        return new ResponseEntity<>(HttpStatus.OK);
	    }
	    
	    public void savePaymentUser(PaymentRequest request) {
	    	System.out.println("request email address.." + request.getAmount());
	    	Payment paymentValue = new Payment();
	        paymentValue.setUserEmail(request.getEmail());
	        paymentValue.setAmount(request.getAmount());  
	        paymentValue.setProductName(request.getProductName());
	       paymentRepository.save(paymentValue);
	    }
}
