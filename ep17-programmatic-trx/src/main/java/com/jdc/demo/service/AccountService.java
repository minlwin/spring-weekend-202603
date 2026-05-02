package com.jdc.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.demo.model.BusinessException;
import com.jdc.demo.model.output.AccountInfo;
import com.jdc.demo.model.repo.AccountRepo;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepo repo;

	@Transactional(readOnly = true)
	public AccountInfo findByCode(String code) {
		return repo.findById(code)
				.orElseThrow(() -> new BusinessException("There is no account with code %s.".formatted(code)));
	}

	@Transactional
	public void withdraw(AccountInfo account, int amount) {
		var netAmount = account.amount() - amount;
		repo.update(account.code(), netAmount);
	}

	@Transactional
	public void deposit(AccountInfo account, int amount) {
		var netAmount = account.amount() + amount;
		repo.update(account.code(), netAmount);
	}
}
