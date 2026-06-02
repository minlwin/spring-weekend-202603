package com.jdc.demo.domain.output;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.jdc.demo.domain.entity.Course.Level;
import com.jdc.demo.domain.entity.Registration;

import lombok.Data;

@Data
public class RegistrationDetails {

	private UUID id;
	
	private int studentId;
	private String studentName;
	private String phone;
	private String email;
	
	private int classId;
	private int courseId;
	private String courseName;
	private Level courseLevel;
	private LocalDate startDate;
	
	private LocalDateTime registerAt;
	private int paid;

	public static RegistrationDetails from(Registration entity) {
		var dto = new RegistrationDetails();
		
		dto.setId(entity.getId());
		dto.setStudentId(entity.getStudent().getId());
		dto.setStudentName(entity.getStudent().getName());
		dto.setPhone(entity.getStudent().getPhone());
		dto.setEmail(entity.getStudent().getEmail());
		
		dto.setClassId(entity.getIntake().getId());
		dto.setCourseId(entity.getIntake().getCourse().getId());
		dto.setCourseName(entity.getIntake().getCourse().getName());
		dto.setCourseLevel(entity.getIntake().getCourse().getLevel());
		dto.setStartDate(entity.getIntake().getStartDate());
		
		dto.setRegisterAt(entity.getRegistDate());
		dto.setPaid(entity.getPaid());
		
		return dto;
	}

}
