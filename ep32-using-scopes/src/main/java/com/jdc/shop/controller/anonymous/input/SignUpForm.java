package com.jdc.shop.controller.anonymous.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpForm {

	@NotBlank(message = "Enter your name.")
	private String name;
	@NotBlank(message = "Enter your email.")
	private String email;
	@NotBlank(message = "Enter Password.")
	private String password;
}
