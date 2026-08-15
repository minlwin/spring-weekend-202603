package com.jdc.spring.demo.api.anonymous.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.jdc.spring.demo.api.anonymous.output.AuthResult;
import com.jdc.spring.demo.model.entity.Account.Role;
import com.jdc.spring.demo.utils.security.ApplicationUser;
import com.jdc.spring.demo.utils.security.JwtTokenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationResultService {
	
	private final JwtTokenService tokenService;

	public AuthResult create(Authentication authentication) {
		
		var user = (ApplicationUser)authentication.getPrincipal();
		var role = user.getAuthorities().stream().map(a -> a.getAuthority())
			.findFirst().get();		
		
		return AuthResult.builder()
				.name(user.getName())
				.email(user.getUsername())
				.role(Role.valueOf(role))
				.accessToken(tokenService.generateAccess(authentication))
				.refreshToken(tokenService.generateRefresh(authentication))
				.build();
	}

	public AuthResult refresh(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
