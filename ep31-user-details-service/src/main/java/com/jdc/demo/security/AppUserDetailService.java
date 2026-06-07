package com.jdc.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jdc.demo.model.repo.AccountRepo;

@Service
public class AppUserDetailService implements UserDetailsService{

	@Autowired
	private AccountRepo accountRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return accountRepo.findOneByEmail(username)
				.map(account -> User.withUsername(username)
						.password(account.getPassword())
						.roles(account.getRole().name())
						.disabled(!account.isActivated())
						.build())
				.orElseThrow();
	}

}
