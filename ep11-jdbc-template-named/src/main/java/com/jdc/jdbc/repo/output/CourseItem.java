package com.jdc.jdbc.repo.output;

public record CourseItem(
		int id,
		String name,
		int fees,
		int hours,
		String description) {

}
