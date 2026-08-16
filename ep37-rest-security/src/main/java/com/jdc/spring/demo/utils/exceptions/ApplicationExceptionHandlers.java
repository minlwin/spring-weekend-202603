package com.jdc.spring.demo.utils.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandlers {

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	List<String> handle(MethodArgumentNotValidException e) {
		return e.getFieldErrors().stream()
				.map(a -> a.getDefaultMessage()).toList();
	}
	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	List<String> handle(BusinessRuleViolationException e) {
		return List.of(e.getMessage());
	}
	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.GONE)
	List<String> handle(TokenAccessExpiredException e) {
		return List.of(e.getMessage());
	}
	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	List<String> handle(AuthenticationException e) {
		return List.of(switch(e) {
		case UsernameNotFoundException _-> "Please check your email.";
		case BadCredentialsException _-> "Please check your password.";
		case DisabledException _-> "You need to verify first. Please check your email.";
		case AccountExpiredException _-> "Your account has been expired.";
		default -> "Authentication Fails.";
		});
	}
	
	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	List<String> handle(Throwable e) {
		log.error("Fatal Error : ", e);
		return List.of(e.getMessage());
	}
}
