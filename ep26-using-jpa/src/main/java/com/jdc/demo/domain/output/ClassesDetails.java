package com.jdc.demo.domain.output;

import java.time.LocalDate;
import java.util.List;

import com.jdc.demo.domain.embeddables.Schedule;
import com.jdc.demo.domain.entity.Classes;
import com.jdc.demo.domain.entity.Classes.Status;
import com.jdc.demo.domain.entity.Course.Level;

import lombok.Data;

@Data
public class ClassesDetails {

	private int id;
	private int courseId;
	private String courseName;
	private Level courseLevel;
	private String courseDescription;
	
	private LocalDate startDate;
	private Integer months;
	private Integer fees;
	private int availableSeats;
	private int registrations;
	private Status status;
	
	private List<Schedule> schedules;
	
	public int getPercent() {
		return registrations / availableSeats * 100;
	}

	public static ClassesDetails from(Classes entity) {
		var dto = new ClassesDetails();
		dto.setId(entity.getId());
		dto.setCourseId(entity.getCourse().getId());
		dto.setCourseName(entity.getCourse().getName());
		dto.setCourseLevel(entity.getCourse().getLevel());
		dto.setCourseDescription(entity.getCourse().getDescription());
		dto.setStartDate(entity.getStartDate());
		dto.setMonths(entity.getMonths());
		dto.setFees(entity.getFees());
		dto.setAvailableSeats(entity.getAvailableSeats());
		dto.setStatus(entity.getStatus());
		dto.setSchedules(entity.getSchedules());
		dto.setRegistrations(entity.getRegistrations().size());
		return dto;
	}
}
