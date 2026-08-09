package com.jdc.spring.demo.api.anonymous.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignUpForm(
		@NotBlank(message = "Please enter customer name.")
		String name,
		@NotBlank(message = "Please enter email.")
		@Email(message = "Please enter a valid email.")
		String email,
		@NotBlank(message = "Please enter password.")
		String password) {

}
