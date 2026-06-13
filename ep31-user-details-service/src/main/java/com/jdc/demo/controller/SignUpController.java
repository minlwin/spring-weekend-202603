package com.jdc.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jdc.demo.model.input.SignUpForm;
import com.jdc.demo.model.service.SignUpService;

@Controller
@RequestMapping("signup")
public class SignUpController {
	
	@Autowired
	private SignUpService service;

	@GetMapping
	String index() {
		return "pages/signup";
	}
	
	@PostMapping
	String signUp(
			@ModelAttribute("signUpForm") @Validated SignUpForm form, 
			BindingResult result, RedirectAttributes redirect) {
		
		if(result.hasErrors()) {
			return "pages/signup";
		}
		
		service.signUp(form);
		redirect.addFlashAttribute("message", "Your account has been created successfully.");
		
		return "redirect:/login";
	}
	
	@ModelAttribute
	SignUpForm form() {
		return new SignUpForm();
	}
}
