package com.jdc.spring.demo.api.anonymous;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.spring.demo.api.anonymous.input.SignUpForm;
import com.jdc.spring.demo.api.anonymous.output.AuthResult;

@RestController
@RequestMapping("auth/signup")
public class SignUpApi {

	@PostMapping
	AuthResult signUp(@RequestBody @Validated SignUpForm form) {
		return null;
	}	
}
