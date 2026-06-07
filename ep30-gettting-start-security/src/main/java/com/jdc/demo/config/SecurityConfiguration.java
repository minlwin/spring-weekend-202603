package com.jdc.demo.config;


import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) {
		
		http.authorizeHttpRequests(request -> {
			request.requestMatchers("/admin/**").hasRole("ADMIN");
			request.requestMatchers("/member/**").hasAnyRole("MEMBER", "ADMIN");
			request.requestMatchers("/", "/home", "/login").permitAll();
		});
		
		http.formLogin(withDefaults());
		
		http.exceptionHandling(exception -> {
			exception.accessDeniedPage("/home?forbidden=1");
		});
		
		return http.build();
	}
	
}
