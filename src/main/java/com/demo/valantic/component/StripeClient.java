/**
 * 
 */
package com.demo.valantic.component;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Customer;

/**
 * 
 */
@Component
public class StripeClient {

	@Autowired
	StripeClient() {
		Stripe.apiKey = "sk_test_51PpsMoRqEfBuoQzwGnIScRWGS3VCqJQr2ovccb3tQpFcxkh2XiLky8dC5O4AMdJnVmY6UL8jWwPxc0TsVzsrWGta00JxTKpUAh";
	}

	public Customer createCustomer(String token, String email) throws Exception {
		Map<String, Object> customerParams = new HashMap<String, Object>();
		customerParams.put("email", email);
		customerParams.put("source", token);
		return Customer.create(customerParams);
	}

	private Customer getCustomer(String id) throws Exception {
		return Customer.retrieve(id);
	}

	public Charge chargeNewCard(String token, double amount) throws Exception {
		Map<String, Object> chargeParams = new HashMap<String, Object>();
		chargeParams.put("amount", (int) (amount * 100));
		chargeParams.put("currency", "USD");
		chargeParams.put("source", token);
		Charge charge = Charge.create(chargeParams);

		return charge;
	}

	public Charge chargeCustomerCard(String customerId, int amount) throws Exception {
		String sourceCard = getCustomer(customerId).getDefaultSource();
		Map<String, Object> chargeParams = new HashMap<String, Object>();
		chargeParams.put("amount", amount);
		chargeParams.put("currency", "USD");
		chargeParams.put("customer", customerId);
		chargeParams.put("source", sourceCard);
		Charge charge = Charge.create(chargeParams);
		return charge;
	}

}
