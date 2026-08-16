package com.jdc.spring.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.jdc.spring.demo.utils.exceptions.SecurityExceptionHandler;
import com.jdc.spring.demo.utils.security.JwtTokenFilter;
import com.jdc.spring.demo.utils.security.JwtTokenService;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, SecurityExceptionHandler securityExceptionHandler) {
		
		http.csrf(csrf -> csrf.disable());
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.authorizeHttpRequests(request -> {
			request.requestMatchers("/auth/**", "/resources/**").permitAll();
			request.requestMatchers("/management/**").hasAnyRole("Employee", "Admin");
			request.requestMatchers("/member/**").hasRole("Customer");
			request.anyRequest().authenticated();
		});
		
		var jwtTokenFilter = new JwtTokenFilter(jwtTokenService());
		http.addFilterBefore(jwtTokenFilter, AuthorizationFilter.class);
		
		http.exceptionHandling(exception -> {
			exception.authenticationEntryPoint(securityExceptionHandler);
			exception.accessDeniedHandler(securityExceptionHandler);
		});
		
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	JwtTokenService jwtTokenService() {
		return new JwtTokenService();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	SecurityExceptionHandler securityExceptionHandler(HandlerExceptionResolver handlerExceptionResolver) {
		return new SecurityExceptionHandler(handlerExceptionResolver);
	}
}
