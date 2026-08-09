package com.jdc.spring.demo.api.anonymous.output;

import com.jdc.spring.demo.model.entity.Account.Role;

public record AuthResult(
		String name,
		Role role,
		String accessToken,
		String refreshToken) {

}
