package com.jdc.spring.demo.api.member.service;

import static com.jdc.spring.demo.utils.OptionalsUtils.safeCall;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.demo.api.management.output.CustomerDetails;
import com.jdc.spring.demo.api.member.input.ProfileForm;
import com.jdc.spring.demo.model.ModificationResult;
import com.jdc.spring.demo.model.repo.AccountRepo;
import com.jdc.spring.demo.model.repo.CustomerRepo;
import com.jdc.spring.demo.utils.exceptions.BusinessRuleViolationException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberProfileService {
	
	private final CustomerRepo customerRepo;
	private final AccountRepo accountRepo;
	
	@Transactional(readOnly = true)
	@PreAuthorize("#username eq authentication.name")
	public CustomerDetails getProfile(String username) {
		return safeCall(customerRepo.findOneByAccountEmail(username).map(CustomerDetails::from))
				.apply("member").apply("email").apply(username);
	}
	
	@Transactional
	@PreAuthorize("#username eq authentication.name")
	public ModificationResult<Integer> update(String username, ProfileForm form) {
		
		var entity = safeCall(customerRepo.findOneByAccountEmail(username))
				.apply("member").apply("email").apply(username);
		
		if(!username.equals(form.email())) {
			if(accountRepo.findOneByEmail(form.email()).isPresent()) {
				throw new BusinessRuleViolationException("%s is already used in other account. Please check your email.".formatted(form.email()));
			}
			entity.getAccount().setEmail(form.email());
		}
		
		entity.getAccount().setName(form.name());
		entity.setPhone(form.phone());
		entity.setDob(form.dob());
		
		return new ModificationResult<>(entity.getId());
	}

}
