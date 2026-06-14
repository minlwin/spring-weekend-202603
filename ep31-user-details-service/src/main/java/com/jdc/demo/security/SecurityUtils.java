package com.jdc.demo.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class SecurityUtils {

	public static boolean hasRole(String roleName) {
		var role = "ROLE_%s".formatted(roleName);
		
		return SecurityContextHolder.getContext().getAuthentication()
			.getAuthorities().stream()
			.filter(a -> a instanceof SimpleGrantedAuthority)
			.map(a -> (SimpleGrantedAuthority)a)
			.map(a -> a.getAuthority())
			.anyMatch(a -> a.equals(role));		
	}

	public static boolean hasAuthority(String roleName) {
		
		return SecurityContextHolder.getContext().getAuthentication()
			.getAuthorities().stream()
			.filter(a -> a instanceof SimpleGrantedAuthority)
			.map(a -> (SimpleGrantedAuthority)a)
			.map(a -> a.getAuthority())
			.anyMatch(a -> a.equals(roleName));		
	}
}
