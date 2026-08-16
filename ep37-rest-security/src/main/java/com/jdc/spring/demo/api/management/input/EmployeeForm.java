package com.jdc.spring.demo.api.management.input;

import com.jdc.spring.demo.model.entity.Account;
import com.jdc.spring.demo.model.entity.Account.Role;

import jakarta.validation.constraints.NotBlank;

public record EmployeeForm(
		@NotBlank(message = "Please enter employee name.")
		String name,
		@NotBlank(message = "Please enter email address.")
		String email,
		@NotBlank(message = "Please enter phone number.")
		String phone) {

	public Account account() {
		var entity = new Account();
		entity.setEmail(email);
		entity.setName(name);
		entity.setRole(Role.Employee);
		return entity;
	}

}
