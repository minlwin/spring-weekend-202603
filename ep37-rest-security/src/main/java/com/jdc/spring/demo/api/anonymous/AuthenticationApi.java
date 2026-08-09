package com.jdc.spring.demo.api.anonymous;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.spring.demo.api.anonymous.input.ActivationForm;
import com.jdc.spring.demo.api.anonymous.input.RefreshTokenForm;
import com.jdc.spring.demo.api.anonymous.input.SignInForm;
import com.jdc.spring.demo.api.anonymous.input.SignUpForm;
import com.jdc.spring.demo.api.anonymous.output.AuthResult;

@RestController
@RequestMapping("auth")
public class AuthenticationApi {

	@PostMapping("signin")
	AuthResult signIn(@RequestBody @Validated SignInForm form) {
		return null;
	}

	@PostMapping("refresh")
	AuthResult refresh(@RequestBody @Validated RefreshTokenForm form) {
		return null;
	}
	
	@PostMapping("signup")
	AuthResult signUp(@RequestBody @Validated SignUpForm form) {
		return null;
	}	
	
	@PostMapping("activate")
	AuthResult activate(@RequestBody @Validated ActivationForm form) {
		return null;
	}	
	
}
