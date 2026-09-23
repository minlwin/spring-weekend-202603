package com.jdc.spring.demo.api.anonymous.service;

import static com.jdc.spring.demo.utils.OptionalsUtils.safeCall;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.demo.api.anonymous.input.ActivationForm;
import com.jdc.spring.demo.api.anonymous.input.ResendOtpForm;
import com.jdc.spring.demo.api.anonymous.output.AuthResult;
import com.jdc.spring.demo.model.ModificationResult;
import com.jdc.spring.demo.model.entity.Account.Role;
import com.jdc.spring.demo.model.entity.VerificationHistory.Action;
import com.jdc.spring.demo.model.repo.AccountRepo;
import com.jdc.spring.demo.model.repo.CandidateRepo;
import com.jdc.spring.demo.model.repo.EmployeeRepo;
import com.jdc.spring.demo.model.repo.PartnerMemberRepo;
import com.jdc.spring.demo.model.repo.PartnerRepo;
import com.jdc.spring.demo.model.service.AccountVerificationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountActivationService {
	
	private final AccountRepo accountRepo;
	private final EmployeeRepo employeeRepo;
	private final PartnerRepo partnerRepo;
	private final PartnerMemberRepo memberRepo;
	private final CandidateRepo candidateRepo;
	private final AccountVerificationService verificationService;
	
	private final AuthenticationManager authenticationManager;
	private final AuthenticationResultService resultService;
	
	private final PasswordEncoder passwordEncoder;
	
	@Transactional
	public AuthResult activate(ActivationForm form) {
		
		var account = verificationService.verify(form.email(), form.securityCode());
		account = accountRepo.getReferenceById(account.getId());
		account.setPassword(passwordEncoder.encode(form.password()));
		
		if(account.getRole() == Role.Candidate) {
			var candidate = candidateRepo.getReferenceById(account.getId());
			candidate.setVerifiedAt(LocalDateTime.now());
		} else if (account.getRole() == Role.Partner) {
			var partner = partnerRepo.getReferenceById(account.getId());
			partner.setVerifiedAt(LocalDateTime.now());
		} else if (account.getRole() == Role.Member) {
			var member = memberRepo.getReferenceById(account.getId());
			member.setVerifiedAt(LocalDateTime.now());
		} else if (account.getRole() == Role.Employee) {
			var employee = employeeRepo.getReferenceById(account.getId());
			employee.setActivatedAt(LocalDateTime.now());
		}
		
		var authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(form.email(), form.password()));
		return resultService.create(authentication);
	}

	@Transactional
	public ModificationResult<UUID> resend(ResendOtpForm form) {
		var account = safeCall(accountRepo.findOneByEmail(form.email()))
				.apply("account").apply("email").apply(form.email());
		
		var history = verificationService.sendVerification(account, Action.AccountActivation);
		
		return new ModificationResult<UUID>(history.getId());
	}

}
