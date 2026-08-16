package com.jdc.spring.demo.utils.exceptions;

import org.springframework.security.core.AuthenticationException;

public class TokenAccessExpiredException extends AuthenticationException {

	private static final long serialVersionUID = 1L;
	
	public TokenAccessExpiredException(Throwable cause) {
		super("Access token has been expired. Please refresh again.", cause);
	}
}
