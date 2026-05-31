package com.jdc.demo.domain.input;

import com.jdc.demo.domain.entity.Course.Level;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseSearch {

	private Level level;
	private String keyword;
}
