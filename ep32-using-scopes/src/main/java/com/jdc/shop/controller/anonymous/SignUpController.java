package com.jdc.shop.controller.anonymous;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.shop.controller.anonymous.input.SignUpForm;
import com.jdc.shop.model.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("auth/signup")
public class SignUpController {
	
	private final CustomerService customerService;
	private final AuthenticationManager authenticationManager;
	private final SecurityContextRepository securityContextRepository;

	@GetMapping
	String signUp() {
		return "pages/auth/signup";
	}

	@PostMapping
	String signUp(@Validated @ModelAttribute("signUpForm") SignUpForm form,
			BindingResult result,
			HttpServletRequest request,
			HttpServletResponse response) {

		if (result.hasErrors()) {
			return "pages/auth/signup";
		}

		try {
			customerService.create(form);
		} catch (IllegalArgumentException ex) {
			result.rejectValue("email", "duplicate", ex.getMessage());
			return "pages/auth/signup";
		}

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(form.getEmail().trim(), form.getPassword()));
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authentication);
		SecurityContextHolder.setContext(context);
		securityContextRepository.saveContext(context, request, response);

		return "redirect:/anonymous/welcome";
	}

	@ModelAttribute(name = "signUpForm")
	SignUpForm form() {
		return new SignUpForm();
	}
}
