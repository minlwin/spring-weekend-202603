package com.jdc.spring.demo.api.anonymous;

import java.util.UUID;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.spring.demo.api.anonymous.input.ActivationForm;
import com.jdc.spring.demo.api.anonymous.input.ResendOtpForm;
import com.jdc.spring.demo.api.anonymous.output.AuthResult;
import com.jdc.spring.demo.api.anonymous.service.AccountActivationService;
import com.jdc.spring.demo.model.ModificationResult;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth/activate")
public class ActivationApi {
	
	private final AccountActivationService service;
	
	@PostMapping
	AuthResult activate(@RequestBody @Validated ActivationForm form) {
		return service.activate(form);
	}	
	
	@PostMapping("resend")
	ModificationResult<UUID> resend(@RequestBody @Validated ResendOtpForm form) {
		return service.resend(form);
	}
}
