package com.jdc.spring.demo.api.anonymous.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.jdc.spring.demo.api.anonymous.input.SignUpForm;
import com.jdc.spring.demo.api.anonymous.input.SignUpForm.Type;
import com.jdc.spring.demo.api.anonymous.output.SignUpResult;
import com.jdc.spring.demo.model.entity.Account;
import com.jdc.spring.demo.model.entity.Candidate;
import com.jdc.spring.demo.model.entity.Partner;
import com.jdc.spring.demo.model.entity.VerificationHistory.Action;
import com.jdc.spring.demo.model.repo.AccountRepo;
import com.jdc.spring.demo.model.repo.CandidateRepo;
import com.jdc.spring.demo.model.repo.PartnerRepo;
import com.jdc.spring.demo.model.service.AccountVerificationService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignUpService {

	private final AccountRepo accountRepo;
	private final PartnerRepo partnerRepo;
	private final CandidateRepo candidateRepo;
	private final AccountVerificationService verificationService;
	
	@Transactional
	public SignUpResult signUp(SignUpForm form) {
		
		var account = accountRepo.save(form.getAccount());
		
		if(form.type() == Type.Partner) {
			return createPartner(account);
		} 
		
		return createCandidate(account);
	}

	private SignUpResult createPartner(Account account) {
		
		// Create Partner
		var partner = new Partner();
		partner.setAccount(account);
		partner.setRegisterdAt(LocalDateTime.now());
		partnerRepo.save(partner);
		
		// Send Message 
		verificationService.sendVerification(account, Action.AccountActivation);
		
		return new SignUpResult("Partner account is created. Check email and activate your account.");
	}

	private SignUpResult createCandidate(Account account) {
		
		// Create Candidate
		var candidate = new Candidate();
		candidate.setAccount(account);
		candidate.setRegisterdAt(LocalDateTime.now());
		candidateRepo.save(candidate);
		
		// Send Message 
		verificationService.sendVerification(account, Action.AccountActivation);

		return new SignUpResult("Partner account is created. Check email and activate your account.");
	}

}
