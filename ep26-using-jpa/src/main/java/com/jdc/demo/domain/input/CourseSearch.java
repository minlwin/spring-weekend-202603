package com.jdc.demo.domain.input;

import com.jdc.demo.domain.constants.CourseLevel;

import lombok.Data;

@Data
public class CourseSearch {

	private CourseLevel level;
	private String keyword;
}
