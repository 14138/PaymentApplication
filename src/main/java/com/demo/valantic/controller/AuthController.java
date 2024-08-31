package com.demo.valantic.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.valantic.entity.User;
import com.demo.valantic.model.PaymentRequest;
import com.demo.valantic.model.UserDto;
import com.demo.valantic.service.PaymentService;
import com.demo.valantic.service.UserService;
import com.demo.valantic.service.impl.PaymentServiceImpl;

import java.util.List;

@Controller
public class AuthController {
	
	private UserService userService;
	
	@Autowired
	private PaymentServiceImpl paymentServiceImpl;

	@Value("${stripe.api.publicKey}")
	private String publicKey;

	public AuthController(UserService userService) {
		this.userService = userService;
	}
	// user registration form request.

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {

		UserDto userDto = new UserDto();
		model.addAttribute("user", userDto); // model object is used to store data that is entered from form.
		return "register";

	}
	// handler method to handle user registration form submit request.

	@PostMapping("/register")
	public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model) { 

		User existingUser = userService.findByEmail(userDto.getEmail()); 

		if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
			result.rejectValue("email", null, "there is already an account existed with this email");
		}

		if (result.hasErrors()) {
			model.addAttribute("user", userDto);
			return "/register"; 
		}

		userService.saveUser(userDto);
		return "redirect:/register?success"; 

	}

	// handler methods for getting list of users.
	@GetMapping("/users")
	public String users(Model model) {
		List<UserDto> users = userService.findAllUsers();
		model.addAttribute("users", users);
		return "users";
	}

	// handler methods for handling login request.

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/index")
	public String home(Model model) {
		model.addAttribute("request", new PaymentRequest());
		return "index";
	}

	@PostMapping("/index")
	public String showCard(@ModelAttribute @Valid PaymentRequest request, BindingResult bindingResult, Model model) throws Exception {
		if (bindingResult.hasErrors()) {
			return "index";
		}
		model.addAttribute("publicKey", publicKey);
		model.addAttribute("amount", request.getAmount());
		model.addAttribute("email", request.getEmail());
		model.addAttribute("currency", request.getCurrency());
		model.addAttribute("productName", request.getProductName());
		paymentServiceImpl.stripePayment(request);
		return "checkout";
	}

	
	  @GetMapping("/checkout") public String checkout(){ return "checkout"; }
	  
	 
	  @PostMapping("/checkout")
		public String checkout(@ModelAttribute @Valid PaymentRequest request, BindingResult bindingResult, Model model) throws Exception {
			if (bindingResult.hasErrors()) {
				return "login";
			}
			model.addAttribute("publicKey", publicKey);
			model.addAttribute("amount", request.getAmount());
			model.addAttribute("email", request.getEmail());
			model.addAttribute("currency", "usd");
			model.addAttribute("productName", request.getProductName());
			paymentServiceImpl.stripePayment(request);
			return "checkout";
		}
	  
	  
		@PostMapping("/login")
		public String PostLogin(Model model) {
			model.addAttribute("request", new PaymentRequest());
			return "index";
		}
	  
}
