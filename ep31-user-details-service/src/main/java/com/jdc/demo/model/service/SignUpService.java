package com.jdc.demo.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.demo.exceptions.AppBusinessException;
import com.jdc.demo.model.entity.Account;
import com.jdc.demo.model.entity.Account.Role;
import com.jdc.demo.model.entity.Member;
import com.jdc.demo.model.input.SignUpForm;
import com.jdc.demo.model.repo.AccountRepo;
import com.jdc.demo.model.repo.MemberRepo;

@Service
public class SignUpService {
	
	@Autowired
	private MemberRepo memberRepo;
	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public void signUp(SignUpForm form) {
		
		if(accountRepo.countByEmail(form.getEmail()) > 0) {
			throw new AppBusinessException("%s is used by other account. Please check your email.".formatted(form.getEmail()));
		}
		
		var account = new Account();
		account.setEmail(form.getEmail());
		account.setRole(Role.Member);
		account.setPassword(passwordEncoder.encode(form.getPassword()));
		
		var member = new Member();
		member.setAccount(account);
		member.setName(form.getName());
		
		memberRepo.save(member);	
	}

}
