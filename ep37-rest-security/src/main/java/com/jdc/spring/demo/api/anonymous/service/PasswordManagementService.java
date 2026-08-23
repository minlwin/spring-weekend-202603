package com.jdc.spring.demo.api.anonymous.service;

import static com.jdc.spring.demo.utils.OptionalsUtils.safeCall;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.spring.demo.api.anonymous.input.ForgotPasswordForm;
import com.jdc.spring.demo.api.anonymous.input.ResetPasswordForm;
import com.jdc.spring.demo.model.entity.VerificationHistory;
import com.jdc.spring.demo.model.entity.VerificationHistory.Action;
import com.jdc.spring.demo.model.repo.AccountRepo;
import com.jdc.spring.demo.model.service.AccountVerificationService;
import com.jdc.spring.demo.utils.exceptions.BusinessRuleViolationException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PasswordManagementService {

	private final AccountRepo accountRepo;
	private final PasswordEncoder passwordEncoder;
	private final AccountVerificationService verificationService;

	@Transactional
	public VerificationHistory forgot(ForgotPasswordForm form) {
		
		var account = safeCall(accountRepo.findOneByEmail(form.email()))
				.apply("Account").apply("email").apply(form.email());
		
		if(!StringUtils.hasLength(account.getPassword())) {
			throw new BusinessRuleViolationException("You have to activate your account first.");
		}
		
		return verificationService.sendVerification(account, Action.ForgotPassword);
	}

	@Transactional
	public void reset(String id, ResetPasswordForm form) {

		var account = verificationService.verify(UUID.fromString(id), form.securityCode());
		
		account = accountRepo.getReferenceById(account.getId());
		
		account.setPassword(passwordEncoder.encode(form.password()));
	}

}
