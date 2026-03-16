package com.jdc.jdbc.repo.input;

public record CourseForm(
		String name,
		int hours,
		int fees,
		String description) {

}
