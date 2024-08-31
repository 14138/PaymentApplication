package com.demo.valantic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.demo.valantic.entity.Payment;
import com.demo.valantic.entity.User;
import com.demo.valantic.model.PaymentRequest;
import com.demo.valantic.model.UserDto;
import com.demo.valantic.repository.PaymentRepository;
import com.demo.valantic.service.impl.PaymentServiceImpl;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import jakarta.transaction.Transactional;

public interface PaymentService {
	
	 void savePaymentUser(PaymentRequest payment);

}	
	

   