package com.jdc.demo.domain.input;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jdc.demo.domain.embeddables.Schedule;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClassesForm {
	
	@NotNull(message = "Please select a course.")
	private Integer courseId;
	@NotNull(message = "Please enter start date.")
	private LocalDate startDate;
	@NotNull(message = "Please enter fees.")
	private Integer fees;
	@NotNull(message = "Please enter avialable seats.")
	private Integer availableSeats;
	
	@NotEmpty(message = "Plase set schedule.")
	private List<@Valid Schedule> schedules;

	public void addSchedule(Schedule schedule) {
		if(schedules == null) {
			schedules = new ArrayList<>();
		}
		
		schedules.add(schedule);
	}
	
}
