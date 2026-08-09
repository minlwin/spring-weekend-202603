package com.jdc.spring.demo.api.anonymous.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ActivationForm(
		@NotBlank(message = "Please enter email.")
		@Email(message = "Please enter a valid email.")
		String email,
		@NotBlank(message = "Please enter security code.")
		String securityCode,
		@NotBlank(message = "Please enter password.")
		String password) {

}
