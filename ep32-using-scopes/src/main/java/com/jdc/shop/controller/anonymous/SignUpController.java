package com.jdc.shop.controller.anonymous;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.shop.controller.anonymous.input.SignUpForm;

@Controller
@RequestMapping("auth/signup")
public class SignUpController {
	
	@GetMapping
	String signUp() {
		return "pages/auth/signup";
	}
	
	@PostMapping
	String signUp(@Validated @ModelAttribute("signUpForm") SignUpForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			return "pages/auth/signup";
		}
		
		// Create Customer
		
		// Programmatic Authentication
		
		return "";
	}

	
	@ModelAttribute
	SignUpForm form() {
		return new SignUpForm();
	}
}
