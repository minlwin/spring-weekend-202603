package com.jdc.spring.demo.api.anonymous;

import java.util.UUID;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.spring.demo.api.anonymous.input.ForgotPasswordForm;
import com.jdc.spring.demo.api.anonymous.input.ResetPasswordForm;
import com.jdc.spring.demo.api.anonymous.service.PasswordManagementService;
import com.jdc.spring.demo.model.ModificationResult;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("password")
public class PasswordRecoveryApi {
	
	private final PasswordManagementService service;

	@PostMapping("forgot")
	ModificationResult<UUID> requestForgotPassword(
			@Validated @RequestBody ForgotPasswordForm form) {
		var result = service.forgot(form);
		return new ModificationResult<UUID>(result.getId());
	}
	
	@PostMapping("resend")
	ModificationResult<UUID> resend(
			@Validated @RequestBody ForgotPasswordForm form) {
		var result = service.forgot(form);
		return new ModificationResult<UUID>(result.getId());
	}

	@PostMapping("{id}/reset")
	ModificationResult<String> resetPassword(
			@PathVariable String id, 
			@Validated @RequestBody ResetPasswordForm form) {
		service.reset(id, form);
		return new ModificationResult<>("Your password has ben reset, please login again.");
	}
}
