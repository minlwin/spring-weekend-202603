package com.jdc.spring.demo.api.candidate.input;

import java.time.LocalDate;

import com.jdc.spring.demo.model.entity.Candidate;
import com.jdc.spring.demo.model.entity.Candidate.Gender;
import com.jdc.spring.demo.model.entity.Candidate.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PersonalInfoForm(
		@NotBlank(message = "Please enter your name.")
		String name,
		@NotNull(message = "Please enter date of birth.")
		LocalDate dob,
		@NotNull(message = "Please select gender.")
		Gender gender,
		@NotBlank(message = "Please enter phone number.")
		String phone,
		@NotBlank(message = "Please enter email address.")
		String email,
		@NotBlank(message = "Please enter job title.")
		String jobTitle,
		int expectedSalaryFrom,
		int expectedSalaryTo,
		Status status,
		String biography) {

	public void setValues(Candidate entity) {
		var account = entity.getAccount();
		account.setEmail(email);
		account.setName(name);
		
		entity.setDob(dob);
		entity.setGender(gender);
		entity.setPhone(phone);
		entity.setJobTitle(jobTitle);
		entity.setExpectedSalaryFrom(expectedSalaryFrom);
		entity.setExpectedSalaryTo(expectedSalaryTo);
		entity.setStatus(status);
		entity.setBiography(biography);
	}

}
