package com.jdc.spring.demo.api.anonymous.input;

import com.jdc.spring.demo.model.entity.Account;
import com.jdc.spring.demo.model.entity.Account.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignUpForm(
		@NotBlank(message = "Please enter customer name.")
		String name,
		@NotBlank(message = "Please enter email.")
		@Email(message = "Please enter a valid email.")
		String email) {

	public Account getAccount() {
		var account = new Account();
		account.setName(name);
		account.setEmail(email);
		account.setRole(Role.Customer);
		return account;
	}

}
