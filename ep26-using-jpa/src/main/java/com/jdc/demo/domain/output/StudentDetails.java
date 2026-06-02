package com.jdc.demo.domain.output;

import java.time.LocalDateTime;
import java.util.List;

import com.jdc.demo.domain.embeddables.Parent;
import com.jdc.demo.domain.entity.Student;

import lombok.Data;

@Data
public class StudentDetails {
	
	private int id;
	private String name;
	private String phone;
	private String email;
	private LocalDateTime registerAt;
	
	private Parent father;
	private Parent mother;
	
	private List<RegistrationShortInfo> registrations;

	public static StudentDetails from(Student entity) {
		var dto = new StudentDetails();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setPhone(entity.getPhone());
		dto.setRegisterAt(entity.getCreatedAt());
		
		dto.setFather(entity.getFather());
		dto.setMother(entity.getMother());
		
		dto.setRegistrations(entity.getRegistrations().stream().map(RegistrationShortInfo::from).toList());
		
		return dto;
	}

}
