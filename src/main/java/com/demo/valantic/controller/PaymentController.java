package com.demo.valantic.controller;

import com.demo.valantic.model.PaymentRequest;
import com.demo.valantic.model.PaymentResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCaptureParams;
import com.stripe.param.PaymentIntentCreateParams;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment/secure")
public class PaymentController {
    @PostMapping("/create-payment-intent")
    public PaymentResponse createPaymentIntent(@RequestBody PaymentRequest request)
            throws StripeException {
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(request.getAmount() * 100L)
                        .putMetadata("productName",
                                request.getProductName())
                        .setCurrency("usd")
                        .setAutomaticPaymentMethods(
                                PaymentIntentCreateParams
                                        .AutomaticPaymentMethods
                                        .builder()
                                        .setEnabled(true)
                                        .build()
                        )
                        .build();

        PaymentIntent intent =
                PaymentIntent.create(params);

        return new PaymentResponse(intent.getId(),
                intent.getClientSecret());
    }
    
    @PostMapping("/create_refund/{id}")

    public ResponseEntity<String> createRefund(@PathVariable String id) throws StripeException {
	  PaymentIntent pId = PaymentIntent.retrieve(id);
		
		PaymentIntent refund=pId.cancel();
      return new ResponseEntity<>(refund.toString(),HttpStatus.OK);

  	}
    
    @PostMapping("/payment_intents/{id}/capture")
    public ResponseEntity<String> captureIntent(@PathVariable String id) throws StripeException {
    	PaymentIntent pId=PaymentIntent.retrieve(id);
		
		PaymentIntentCaptureParams params = PaymentIntentCaptureParams.builder().build();
		
		PaymentIntent paymentIntent = pId.capture(params);
		
        return new ResponseEntity<>(paymentIntent.toString(),HttpStatus.OK);
    }
    
    @GetMapping("/payment_intents/{id}")
    public ResponseEntity<String> getIntent(@RequestParam String id) throws StripeException {	
		
		PaymentIntent paymentIntents=PaymentIntent.retrieve(id);
        return new ResponseEntity<>(paymentIntents.getClientSecret(),HttpStatus.OK);
    }
}