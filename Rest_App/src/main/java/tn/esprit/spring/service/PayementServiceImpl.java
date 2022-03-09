package tn.esprit.spring.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;

import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;



@Service
public class PayementServiceImpl implements IPaymentService {


	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void createStripeCustomer(int idUser) {
		Stripe.apiKey = "sk_test_51Ka6odLL6DNGUXDONA7hEktlaBaoWhiQtapPYfPW0p5usQsvv0MEl3lxTLonBZ256Buhv16wjS7eJbRv67IqkYPI00wBooO9QG";
		User user = userRepository.findById(idUser).get();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("description", "My First Test Customer (created for API docs)");
		params.put("email", user.getEmail());
		try {
		Customer customer = Customer.create(params);
		} catch (StripeException e) {

			throw new RuntimeException(e);
		}

	}

	@Override
	public String retrieveStripeCustomer(String idCus) {
		return " okiiii"+idCus;
	}

	@Override
	public String createCardForCustumorStripe(String customerId, String card, String expMonth, String expYear,
			String cvc) throws StripeException {
		Stripe.apiKey = "sk_test_51Ka6odLL6DNGUXDONA7hEktlaBaoWhiQtapPYfPW0p5usQsvv0MEl3lxTLonBZ256Buhv16wjS7eJbRv67IqkYPI00wBooO9QG";
		Customer customer = Customer.retrieve(customerId);
		Map<String, Object> cardParam = new HashMap<String, Object>();
		cardParam.put("number", card);
		cardParam.put("exp_month", expMonth);
		cardParam.put("exp_year", expYear);
		cardParam.put("cvc", cvc);
		Map<String, Object> tokenParam = new HashMap<String, Object>();
		tokenParam.put("card", cardParam);

		Token token = Token.create(tokenParam);
		Map<String, Object> source = new HashMap<String, Object>();
		source.put("source", token.getId());

		customer.getSources().create(source);
		return token.getId();
	}

	@Override
	public void chargeCustomer(int amount) {
		Stripe.apiKey = "sk_test_51Ka6odLL6DNGUXDONA7hEktlaBaoWhiQtapPYfPW0p5usQsvv0MEl3lxTLonBZ256Buhv16wjS7eJbRv67IqkYPI00wBooO9QG";
		Map<String, Object> params = new HashMap<>();
		params.put("amount", amount);
		params.put("currency", "usd");
		params.put("customer", "cus_LGfTIWOvKWyOag");

		try {
			Charge charge = Charge.create(params);
		} catch (StripeException e) {
			throw new RuntimeException(e);
		}
		
	}

}