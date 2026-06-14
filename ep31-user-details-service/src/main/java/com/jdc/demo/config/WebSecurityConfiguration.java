package com.jdc.demo.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.jdc.demo.model.BaseRepositoryImpl;

@Configuration
@EnableJpaRepositories(
	repositoryBaseClass = BaseRepositoryImpl.class,
	basePackages = "com.jdc.demo.model"
)
public class WebSecurityConfiguration {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) {
		
		http.authorizeHttpRequests(req -> {
			req.requestMatchers("/admin/**").hasRole("Admin");
			req.requestMatchers("/member/**").hasRole("Member");
			req.requestMatchers("/", "/welcome", "/login", "/signup").permitAll();
			req.anyRequest().authenticated();
		});
		
		http.formLogin(form -> {
			form.loginPage("/login");
		});
		
		http.logout(withDefaults());
		
		http.exceptionHandling(exception -> {
			exception.accessDeniedPage("/403");
		});
		
		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
