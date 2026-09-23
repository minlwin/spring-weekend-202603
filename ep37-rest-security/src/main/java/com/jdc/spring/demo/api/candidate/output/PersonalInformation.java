package com.jdc.spring.demo.api.candidate.output;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import com.jdc.spring.demo.model.entity.Candidate;
import com.jdc.spring.demo.model.entity.Candidate.Gender;
import com.jdc.spring.demo.model.entity.Candidate.Status;

public record PersonalInformation(
		int id,
		String name,
		String profileImage,
		LocalDate dob,
		Gender gender,
		String phone,
		String email,
		String jobTitle,
		int expectedSalaryFrom,
		int expectedSalaryTo,
		Status status,
		String biography,
		LocalDateTime registeredAt,
		LocalDateTime activatedAt) {
	
	public String getStatusValue() {
		return Optional.ofNullable(status).map(a -> a.getValue()).orElse("");
	}

	public static PersonalInformation from(Candidate entity) {
		return new PersonalInformation(
				entity.getId(), 
				entity.getAccount().getName(), 
				entity.getSelfie(),
				entity.getDob(), 
				entity.getGender(), 
				entity.getPhone(), 
				entity.getAccount().getEmail(), 
				entity.getJobTitle(), 
				entity.getExpectedSalaryFrom(), 
				entity.getExpectedSalaryTo(), 
				entity.getStatus(), 
				entity.getBiography(), 
				entity.getRegisterdAt(), 
				entity.getVerifiedAt());
	}
}
