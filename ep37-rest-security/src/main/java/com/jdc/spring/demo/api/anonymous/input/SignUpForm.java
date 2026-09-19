package com.jdc.spring.demo.api.anonymous.input;

import com.jdc.spring.demo.model.entity.Account;
import com.jdc.spring.demo.model.entity.Account.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SignUpForm(
		@NotNull(message = "Please select account type.")
		Type type,
		@NotBlank(message = "Please enter customer name.")
		String name,
		@NotBlank(message = "Please enter email.")
		@Email(message = "Please enter a valid email.")
		String email) {

	public Account getAccount() {
		var account = new Account();
		account.setName(name);
		account.setEmail(email);
		account.setRole(type.getRole());
		return account;
	}
	
	public enum Type {
		Candidate, Partner;
		
		public Role getRole() {
			return Role.valueOf(name());
		}
	}

}
