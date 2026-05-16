package com.jdc.demo.domain.input;

import com.jdc.demo.domain.output.CourseDetails;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CourseForm {

	@NotBlank(message = "Please enter course name")
	private String name;
	
	@NotBlank(message = "Please select course level")
	private String level;
	
	@NotNull(message = "Please enter course hours")
	private Integer hours;
	
	private String description;

	public void setDetails(CourseDetails details) {
		this.name = details.getName();
		this.level = details.getLevel().name();
		this.hours = details.getHours();
		this.description = details.getDescription();
	}
}
