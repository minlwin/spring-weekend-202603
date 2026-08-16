package com.jdc.spring.demo.api.anonymous.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.demo.api.anonymous.input.SignUpForm;
import com.jdc.spring.demo.api.anonymous.output.SignUpResult;
import com.jdc.spring.demo.model.entity.Customer;
import com.jdc.spring.demo.model.entity.VerificationHistory.Action;
import com.jdc.spring.demo.model.repo.AccountRepo;
import com.jdc.spring.demo.model.repo.CustomerRepo;
import com.jdc.spring.demo.model.service.AccountVerificationService;
import com.jdc.spring.demo.utils.exceptions.BusinessRuleViolationException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerSignUpService {

	private final AccountRepo accountRepo;
	private final CustomerRepo customerRepo;
	private final AccountVerificationService verificationService;
	
	@Transactional
	public SignUpResult signUp(SignUpForm form) {
		
		if(accountRepo.findOneByEmail(form.email()).isPresent()) {
			throw new BusinessRuleViolationException("%s is already used in other account. Please check your email.".formatted(form.email()));
		}
		
		var account = accountRepo.save(form.getAccount());
		
		var customer = new Customer();
		customer.setAccount(account);
		customer.setRegisterdAt(LocalDateTime.now());
		customer = customerRepo.save(customer);
		
		verificationService.sendVerification(account, Action.CustomerSignUp);
		
		return new SignUpResult("Sign up successfully! Please check your email and activate your account.");
	}

}
