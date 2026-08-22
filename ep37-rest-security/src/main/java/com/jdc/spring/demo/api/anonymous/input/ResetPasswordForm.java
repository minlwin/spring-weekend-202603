package com.jdc.spring.demo.api.anonymous.input;

import jakarta.validation.constraints.NotBlank;

public record ResetPasswordForm(
		@NotBlank(message = "Please enter security code.")
		String securityCode,
		@NotBlank(message = "Please enter password.")
		String password) {

}
