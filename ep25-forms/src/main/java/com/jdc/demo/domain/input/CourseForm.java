package com.jdc.demo.domain.input;

import com.jdc.demo.domain.constants.CourseLevel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CourseForm {

	@NotBlank(message = "Please enter course name")
	private String name;
	
	@NotNull(message = "Please select course level")
	private CourseLevel level;
	
	@NotNull(message = "Please enter course hours")
	private Integer hours;
	
	private String description;
}
