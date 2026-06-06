package com.jdc.demo.domain.output;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.jdc.demo.domain.embeddables.Parent;
import com.jdc.demo.domain.entity.Student;
import com.jdc.demo.domain.entity.Student.Gender;

import lombok.Data;

@Data
public class StudentDetails {

	private int id;
	private String name;
	private String phone;
	private String email;
	private Gender gender;
	private LocalDate dob;
	private LocalDateTime registerAt;

	private Parent father;
	private Parent mother;

	private List<RegistrationShortInfo> registrations;

	public static StudentDetails from(Student entity) {
		var dto = new StudentDetails();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setPhone(entity.getPhone());
		dto.setEmail(entity.getEmail());
		dto.setGender(entity.getGender());
		dto.setDob(entity.getDob());
		dto.setRegisterAt(entity.getCreatedAt());

		dto.setFather(entity.getFather());
		dto.setMother(entity.getMother());

		dto.setRegistrations(entity.getRegistrations().stream().map(RegistrationShortInfo::from).toList());

		return dto;
	}

}
