package com.jdc.spring.demo.api.anonymous.input;

import jakarta.validation.constraints.NotBlank;

public record ResendOtpForm(
		@NotBlank(message = "Please enter email to resend otp.")
		String email) {

}
