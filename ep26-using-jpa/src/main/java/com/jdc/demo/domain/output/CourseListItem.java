package com.jdc.demo.domain.output;

import com.jdc.demo.domain.entity.Course;
import com.jdc.demo.domain.entity.Course.Level;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseListItem {

	private int id;
	private String name;
	private Level level;
	private int hours;
	
	public CourseListItem(Course course) {
		this.id = course.getId();
		this.name = course.getName();
		this.level = course.getLevel();
		this.hours = course.getHours();
	}
	
	public static CourseListItem from(Course course) {
		return new CourseListItem(course.getId(), course.getName(), course.getLevel(), course.getHours());
	}
}
