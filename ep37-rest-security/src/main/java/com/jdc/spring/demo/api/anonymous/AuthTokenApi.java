package com.jdc.spring.demo.api.anonymous;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.spring.demo.api.anonymous.input.RefreshTokenForm;
import com.jdc.spring.demo.api.anonymous.input.SignInForm;
import com.jdc.spring.demo.api.anonymous.output.AuthResult;
import com.jdc.spring.demo.model.service.AuthenticationResultService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth/token")
public class AuthTokenApi {
	
	private final AuthenticationManager authenticationManager;
	private final AuthenticationResultService authResultService;

	@PostMapping("generate")
	AuthResult generate(@RequestBody @Validated SignInForm form) {
		
		var usernameAndPassword = UsernamePasswordAuthenticationToken.unauthenticated(form.email(), form.password());
		var authentication = authenticationManager.authenticate(usernameAndPassword);
		
		return authResultService.create(authentication);
	}

	@PostMapping("refresh")
	AuthResult refresh(@RequestBody @Validated RefreshTokenForm form) {
		return null;
	}

}
