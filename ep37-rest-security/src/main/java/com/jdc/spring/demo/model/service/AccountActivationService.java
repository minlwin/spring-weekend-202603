package com.jdc.spring.demo.model.service;

import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.demo.api.anonymous.input.ActivationForm;
import com.jdc.spring.demo.api.anonymous.output.AuthResult;
import com.jdc.spring.demo.model.entity.Account.Role;
import com.jdc.spring.demo.model.repo.CustomerRepo;
import com.jdc.spring.demo.model.repo.EmployeeRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountActivationService {
	
	private final CustomerRepo customerRepo;
	private final EmployeeRepo employeeRepo;
	private final AccountVerificationService verificationService;
	
	private AuthenticationManager authenticationManager;
	private AuthenticationResultService resultService;
	
	@Transactional
	public AuthResult activate(ActivationForm form) {
		
		var account = verificationService.verify(form);
		
		if(account.getRole() == Role.Customer) {
			var customer = customerRepo.getReferenceById(account.getId());
			customer.setVerifiedAt(LocalDateTime.now());
		} else if (account.getRole() == Role.Employee) {
			var employee = employeeRepo.getReferenceById(account.getId());
			employee.setActivatedAt(LocalDateTime.now());
		}
		
		var authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(form.email(), form.password()));
		return resultService.create(authentication);
	}

}
