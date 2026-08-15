package com.jdc.spring.demo.model.service;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.demo.api.anonymous.input.ActivationForm;
import com.jdc.spring.demo.model.entity.Account;
import com.jdc.spring.demo.model.entity.VerificationHistory;
import com.jdc.spring.demo.model.entity.VerificationHistory.Action;
import com.jdc.spring.demo.model.repo.VerificationHistoryRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountVerificationService {
	
	private final PasswordEncoder encoder;
	private final VerificationHistoryRepo historyRepo;

	@Transactional
	public void signUp(Account account) {
		sendVerification(account, Action.CustomerSignUp);
	}

	private void sendVerification(Account account, Action action) {
		var history = new VerificationHistory();
		history.setAccount(account);
		history.setAction(action);
		
		var otp = ThreadLocalRandom.current().nextInt(1000, 999999);
		// TODO Send Email

		history.setCode(encoder.encode("%06d".formatted(otp)));
		history.setSendAt(LocalDateTime.now());
		
		historyRepo.save(history);
	}

	public Account verify(ActivationForm form) {
		// TODO Auto-generated method stub
		return null;
	}
}
