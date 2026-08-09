package com.jdc.spring.demo.utils.security;

import java.time.LocalDate;
import java.util.function.Function;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.jdc.spring.demo.model.entity.Account;
import com.jdc.spring.demo.model.entity.Account.Role;
import com.jdc.spring.demo.model.repo.AccountRepo;
import com.jdc.spring.demo.model.repo.EmployeeRepo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApplicationUserService implements UserDetailsService {
	
	private final AccountRepo accountRepo;
	private final EmployeeRepo employeeRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Function<Account, ApplicationUser> userConverter = account -> {
			var userDetails = User.withUsername(username)
					.password(account.getPassword())
					.roles(account.getRole().name())
					.disabled(isDisabled(account))
					.accountExpired(isExpired(account))
					.build();
			return new ApplicationUser((User)userDetails, account.getName());
		};
		
		return accountRepo.findOneByEmail(username)
				.map(userConverter)
				.orElseThrow(() -> new UsernameNotFoundException(username));
	}

	private boolean isDisabled(Account account) {
		
		if(account.getRole() == Role.Employee) {
			var employee = employeeRepo.getReferenceById(account.getId());
			return null == employee.getActivatedAt();
		}
		
		return false;
	}

	private boolean isExpired(Account account) {
		
		if(account.getRole() == Role.Employee) {
			var employee = employeeRepo.getReferenceById(account.getId());
			if(employee.getRetiredAt() != null && LocalDate.now().isAfter(employee.getRetiredAt())) {
				return true;
			}
		}
		
		return false;
	}

}
