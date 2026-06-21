package com.jdc.shop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.jdc.shop.model.repository.AccountRepo;

@Component
public class AppUserDetailsService implements UserDetailsService {
	
	@Autowired
	private AccountRepo accountRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return accountRepo.findOneByEmail(username)
				.map(account -> User.builder()
						.username(account.getEmail())
						.password(account.getPassword())
						.authorities(account.getRole().name())
						.build())
				.orElseThrow();
	}

}
