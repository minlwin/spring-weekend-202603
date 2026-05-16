package com.jdc.demo.domain.output;

import com.jdc.demo.domain.constants.CourseLevel;

import lombok.Data;

@Data
public class CourseListItem {

	private int id;
	private String name;
	private CourseLevel level;
	private int hours;
}
