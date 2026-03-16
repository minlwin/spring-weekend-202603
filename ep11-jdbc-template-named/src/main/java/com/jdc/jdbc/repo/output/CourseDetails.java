package com.jdc.jdbc.repo.output;

public record CourseDetails(
		int id,
		String name,
		int fees,
		int hours,
		String description,
		long classes) {

}
