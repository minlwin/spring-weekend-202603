package com.jdc.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import com.jdc.shop.model.BaseRepositoryImpl;

@Configuration
@EnableJpaAuditing
@EnableMethodSecurity
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class SecurityConfiguration {

	@Bean
	SecurityFilterChain httpSecurity(HttpSecurity http, SecurityContextRepository securityContextRepository) {

		http.authorizeHttpRequests(req -> {
			req.requestMatchers("/management/**").hasAnyAuthority("Admin", "Employee");
			req.requestMatchers("/member/**").hasAuthority("Customer");
			req.requestMatchers("/", "/anonymous/**", "/auth/**", "/style/**", "/js/**").permitAll();
			req.anyRequest().authenticated();
		});

		http.securityContext(context -> context.securityContextRepository(securityContextRepository));

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

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
		return config.getAuthenticationManager();
	}

	@Bean
	SecurityContextRepository securityContextRepository() {
		return new HttpSessionSecurityContextRepository();
	}
}
