package com.jdc.spring.demo.utils.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class ApplicationUser extends User implements UserDetails{

	private static final long serialVersionUID = 1L;
	private String name;

	public ApplicationUser(User user, String name) {
		super(
			user.getUsername(), 
			user.getPassword(), 
			user.isEnabled(), 
			user.isAccountNonExpired(), 
			user.isCredentialsNonExpired(), 
			user.isAccountNonLocked(), 
			user.getAuthorities());
		
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
