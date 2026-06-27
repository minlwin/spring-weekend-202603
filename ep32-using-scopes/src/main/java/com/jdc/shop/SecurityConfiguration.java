package com.jdc.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.jdc.shop.model.BaseRepositoryImpl;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class SecurityConfiguration {

	@Bean
	SecurityFilterChain httpSecurity(HttpSecurity http) {
		
		http.authorizeHttpRequests(req -> {
			req.requestMatchers("/management/**").hasAnyAuthority("Admin", "Employee");
			req.requestMatchers("/membrer/**").hasAuthority("Customer");
			req.requestMatchers("/", "/anonymous/**", "/auth/**", "/style/**", "/js/**").permitAll();
			req.anyRequest().authenticated();
		});
		
		http.formLogin(form -> {
			form.loginPage("/auth/signin");
		});
		
		http.logout(logout -> {
			logout.logoutSuccessUrl("/");
		});
		
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
