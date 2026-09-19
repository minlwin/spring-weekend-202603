package com.jdc.spring.demo.api.anonymous;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.spring.demo.api.anonymous.input.SignUpForm;
import com.jdc.spring.demo.api.anonymous.output.SignUpResult;
import com.jdc.spring.demo.api.anonymous.service.SignUpService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth/signup")
public class SignUpApi {
	
	private final SignUpService service;
	
	@PostMapping
	SignUpResult signUp(@RequestBody @Validated SignUpForm form) {
		return service.signUp(form);
	}	
}
