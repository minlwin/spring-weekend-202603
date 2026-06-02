package com.jdc.demo.domain.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegistrationForm {
	@NotBlank(message = "Please enter student name.")
	private String studentName;
	@NotBlank(message = "Please enter email address.")
	private String studentEmail;
	@NotBlank(message = "Please enter phone number.")
	private String studentPhone;
	private String fatherName;
	private String fatherPhone;
	private String fatherOccupation;
	private String motherName;
	private String motherPhone;
	private String motherOccupation;
	private int paid;
}
