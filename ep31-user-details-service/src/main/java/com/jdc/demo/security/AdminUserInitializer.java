package com.jdc.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.demo.model.entity.Account;
import com.jdc.demo.model.entity.Account.Role;
import com.jdc.demo.model.repo.AccountRepo;

@Component
public class AdminUserInitializer {
	
	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	@EventListener(classes = ContextRefreshedEvent.class)
	public void initialize() {
		if(accountRepo.count() == 0) {
			var admin = new Account();
			admin.setActivated(true);
			admin.setEmail("admin@example.com");
			admin.setRole(Role.Admin);
			admin.setName("Admin User");
			admin.setPassword(passwordEncoder.encode("password"));
			accountRepo.save(admin);
		}
	}
}
