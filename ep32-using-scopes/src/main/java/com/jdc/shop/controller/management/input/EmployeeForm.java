package com.jdc.shop.controller.management.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmployeeForm {

	@NotBlank(message = "Enter employee name.")
	private String name;
	@NotBlank(message = "Enter phone number.")
	private String phone;
	@NotBlank(message = "Enter email address.")
	private String email;

}
