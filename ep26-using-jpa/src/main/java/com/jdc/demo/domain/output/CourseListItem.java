package com.jdc.demo.domain.output;

import com.jdc.demo.domain.constants.CourseLevel;
import com.jdc.demo.domain.entity.Course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseListItem {

	private int id;
	private String name;
	private CourseLevel level;
	private int hours;
	
	public static CourseListItem from(Course course) {
		return new CourseListItem(course.getId(), course.getName(), course.getLevel(), course.getHours());
	}
}
