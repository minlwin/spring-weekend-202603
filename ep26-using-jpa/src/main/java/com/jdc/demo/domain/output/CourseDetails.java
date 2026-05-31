package com.jdc.demo.domain.output;

import com.jdc.demo.domain.entity.Course;
import com.jdc.demo.domain.entity.Course.Level;

import lombok.Data;

@Data
public class CourseDetails {
	private int id;
	private String name;
	private Level level;
	private int hours;
	private String description;
	
	public static CourseDetails from(Course course) {
		
		var dto = new CourseDetails();
		dto.setId(course.getId());
		dto.setName(course.getName());
		dto.setLevel(course.getLevel());
		dto.setHours(course.getHours());
		
		return dto;
	}
}
