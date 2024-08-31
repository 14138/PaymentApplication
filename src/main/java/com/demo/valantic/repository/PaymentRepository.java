package com.demo.valantic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.valantic.entity.Payment;
import com.demo.valantic.model.PaymentRequest;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
 
	   Payment findByUserEmail(String userEmail);

	void save(PaymentRequest paymentValue);
}