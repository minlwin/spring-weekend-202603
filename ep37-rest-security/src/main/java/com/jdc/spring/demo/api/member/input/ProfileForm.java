package com.jdc.spring.demo.api.member.input;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfileForm(
		@NotBlank(message = "Please enter name.")
		String name,
		@NotBlank(message = "Please enter phone no.")
		String phone,
		@NotBlank(message = "Please enter email address.")
		String email,
		@NotNull(message = "Please enter date of birth.")
		LocalDate dob) {

}
