package com.jdc.spring.demo.utils.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jdc.spring.demo.model.entity.Account;
import com.jdc.spring.demo.model.entity.Account.Role;
import com.jdc.spring.demo.model.repo.AccountRepo;

@Configuration
public class AdminUserInitializer {

	@Value("${app.admin.user.name}")
	private String name;
	@Value("${app.admin.user.email}")
	private String email;
	@Value("${app.admin.user.password}")
	private String password;
	
	@Bean
	ApplicationRunner applicationRunner(
			AccountRepo repo, 
			PasswordEncoder passwordEncoder) {
		return _ -> {
			if(repo.count() == 0) {
				var admin = new Account();
				admin.setName(name);
				admin.setEmail(email);
				admin.setPassword(passwordEncoder.encode(password));
				admin.setRole(Role.Admin);
				repo.save(admin);
			}
		};
	}
}
