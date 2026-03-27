package com.jdc.jdbc.repo.input;

public record CourseForm(
		String name,
		Integer hours,
		Integer fees,
		String description) {

}
