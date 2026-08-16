package com.jdc.spring.demo.utils.exceptions;

import org.springframework.security.core.AuthenticationException;

public class TokenInvalidateException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public TokenInvalidateException(Throwable cause) {
		super("Invalid jwt token", cause);
	}
}
