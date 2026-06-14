package com.jdc.demo.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProfileForm {

	@NotBlank(message = "Please enter your name.")
	private String name;
	@NotBlank(message = "Please enter phone number.")
	private String phone;
	@NotBlank(message = "Please enter address.")
	private String address;
	
}
