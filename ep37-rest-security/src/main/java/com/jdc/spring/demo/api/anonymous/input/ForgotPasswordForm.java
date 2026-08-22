package com.jdc.spring.demo.api.anonymous.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ForgotPasswordForm(
		@Email(message = "Please enter a valid email.")
		@NotBlank(message = "Please enter email address.")
		String email
		) {

}
