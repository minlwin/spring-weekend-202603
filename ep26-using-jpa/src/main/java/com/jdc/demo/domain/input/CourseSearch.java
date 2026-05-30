package com.jdc.demo.domain.input;

import com.jdc.demo.domain.constants.CourseLevel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseSearch {

	private CourseLevel level;
	private String keyword;
}
