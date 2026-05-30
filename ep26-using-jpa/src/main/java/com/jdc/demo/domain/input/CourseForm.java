package com.jdc.demo.domain.input;

import com.jdc.demo.domain.constants.CourseLevel;
import com.jdc.demo.domain.entity.Course;
import com.jdc.demo.domain.output.CourseDetails;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseForm {

	@NotBlank(message = "Please enter course name")
	private String name;
	
	@NotBlank(message = "Please select course level")
	private CourseLevel level;
	
	@NotNull(message = "Please enter course hours")
	private Integer hours;
	
	private String description;

	public void setDetails(CourseDetails details) {
		this.name = details.getName();
		this.level = details.getLevel();
		this.hours = details.getHours();
		this.description = details.getDescription();
	}

	public Course entity() {
		var entity = new Course();
		entity.setName(name);
		entity.setLevel(level);
		entity.setHours(hours);
		entity.setDescription(description);
		return entity;
	}
}
