package com.jdc.spring.demo.api.anonymous;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.spring.demo.api.anonymous.input.SignUpForm;
import com.jdc.spring.demo.api.anonymous.output.SignUpResult;
import com.jdc.spring.demo.api.anonymous.service.CustomerSignUpService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth/signup")
public class SignUpApi {
	
	private final CustomerSignUpService signUpService;

	@PostMapping
	SignUpResult signUp(@RequestBody @Validated SignUpForm form) {
		return signUpService.signUp(form);
	}	
}
