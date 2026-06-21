package com.jdc.shop.security;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jdc.shop.model.entity.Account;
import com.jdc.shop.model.entity.Account.Role;
import com.jdc.shop.model.repository.AccountRepo;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AdminUserInitializer {

	private final AccountRepo accountRepo;
	private final PasswordEncoder passwordEncoder;

	@Bean
	ApplicationRunner applicationRunner() {
		return _ -> {
			if (accountRepo.count() == 0) {
				var account = new Account();
				account.setEmail("admin@example.com");
				account.setPassword(passwordEncoder.encode("password"));
				account.setRole(Role.Admin);
				accountRepo.save(account);
			}
		};
	}
}
