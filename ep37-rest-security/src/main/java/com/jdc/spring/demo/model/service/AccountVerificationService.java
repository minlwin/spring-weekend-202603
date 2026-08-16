package com.jdc.spring.demo.model.service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.demo.model.ModificationResult;
import com.jdc.spring.demo.model.entity.Account;
import com.jdc.spring.demo.model.entity.Account.Role;
import com.jdc.spring.demo.model.entity.VerificationHistory;
import com.jdc.spring.demo.model.entity.VerificationHistory.Action;
import com.jdc.spring.demo.model.entity.VerificationHistory.Status;
import com.jdc.spring.demo.model.repo.CustomerRepo;
import com.jdc.spring.demo.model.repo.EmployeeRepo;
import com.jdc.spring.demo.model.repo.VerificationHistoryRepo;
import com.jdc.spring.demo.utils.exceptions.BusinessRuleViolationException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountVerificationService {
	
	private final PasswordEncoder encoder;
	private final VerificationHistoryRepo historyRepo;
	private final CustomerRepo customerRepo;
	private final EmployeeRepo employeeRepo;
	
	@Value("${app.setting.otp.otp-life}")
	private int otpLife;
	
	@Value("${app.setting.otp.opt-limit-duration}")
	private int otpLimitDuration;

	@Value("${app.setting.otp.otp-limit-count}")
	private int otpLimitCount;

	@Transactional
	public VerificationHistory sendVerification(Account account, Action action) {
		var history = new VerificationHistory();
		history.setAccount(account);
		history.setAction(action);
		
		var otp = ThreadLocalRandom.current().nextInt(1000, 999999);
		// TODO Send Email

		history.setCode(encoder.encode("%06d".formatted(otp)));
		history.setSendAt(LocalDateTime.now());
		
		historyRepo.save(history);
		
		return history;
	}
	
	@Transactional
	public ModificationResult<UUID> resendVerification(Account account, Action action) {
		
		if(historyRepo.findFialsCount(
				account.getEmail(), 
				action, 
				LocalDateTime.now().minusMinutes(otpLimitDuration), 
				Status.Fails) >= otpLimitCount) {
			throw new BusinessRuleViolationException("You send otp %d times within %d minutes. Please wait and try again."
						.formatted(otpLimitCount, otpLimitDuration));
		}
		
		var result = sendVerification(account, action);
		return new ModificationResult<>(result.getId());
	}
	
	@Transactional(noRollbackFor = BusinessRuleViolationException.class)
	public Account verify(String email, String otp) {
		
		// Find Verification History Data
		var history = historyRepo.findForVerification(email)
				.stream().findFirst()
				.orElseThrow(() -> new BusinessRuleViolationException("There is no otp send history."));
		
		
		history.setVerifiedAt(LocalDateTime.now());
		history.setStatus(Status.Fails);
		var account = history.getAccount();
		
		// Check Expiration
		var expiredAt = history.getSendAt().plusMinutes(otpLife);
		if(LocalDateTime.now().isAfter(expiredAt)) {
			throw new BusinessRuleViolationException("Your one time password has been expired. Please send again.");
		}
		
		// Check Action
		if(account.getRole() == Role.Customer) {
			var customer = customerRepo.getReferenceById(account.getId());
			
			if(history.getAction() == Action.CustomerSignUp && customer.getVerifiedAt() != null) {
				throw new BusinessRuleViolationException("Invalid opt action.");
			}
			
			if(history.getAction() == Action.ForgotPassword && customer.getVerifiedAt() == null) {
				throw new BusinessRuleViolationException("Invalid opt action.");
			}
		} else if (account.getRole() == Role.Employee) {
			var employee = employeeRepo.getReferenceById(account.getId());
			
			if(history.getAction() == Action.ActivateEmployee && employee.getActivatedAt() != null) {
				throw new BusinessRuleViolationException("Invalid opt action.");
			}

			if(history.getAction() == Action.ForgotPassword && employee.getActivatedAt() == null) {
				throw new BusinessRuleViolationException("Invalid opt action.");
			}
		}
		
		// Verify Code
		if(!encoder.matches(otp, history.getCode())) {
			throw new BusinessRuleViolationException("Your one time password is invalid. Please check your mail.");
		}
		
		history.setStatus(Status.Success);
		
		return account;
	}
	
}
