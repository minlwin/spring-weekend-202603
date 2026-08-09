package com.jdc.spring.demo.api.anonymous.input;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenForm(
		@NotBlank(message = "Please enter refresh token.")
		String token) {

}
