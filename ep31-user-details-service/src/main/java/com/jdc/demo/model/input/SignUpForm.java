package com.jdc.demo.model.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpForm {

	@NotBlank(message = "Please enter your name.")
	private String name;
	
	@Email(message = "Please enter a valid email.")
	@NotBlank(message = "Please enter email for login.")
	private String email;
	
	@NotBlank(message = "Please enter password.")
	private String password;
}
