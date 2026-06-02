package com.jdc.demo.domain.output;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.jdc.demo.domain.entity.Registration;

import lombok.Data;

@Data
public class RegistrationShortInfo {

	private UUID id;
	private int classId;
	private int courseId;
	private String courseName;
	private LocalDate startDate;
	private LocalDateTime registeredAt;
	
	public static RegistrationShortInfo from(Registration entity) {
		var dto = new RegistrationShortInfo();
		dto.setId(entity.getId());
		dto.setClassId(entity.getIntake().getId());
		dto.setCourseId(entity.getIntake().getCourse().getId());
		dto.setCourseName(entity.getIntake().getCourse().getName());
		dto.setStartDate(entity.getIntake().getStartDate());
		dto.setRegisteredAt(entity.getCreatedAt());
		return dto;
	}
}
