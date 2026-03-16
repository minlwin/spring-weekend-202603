package com.jdc.jdbc.repo.output;

import java.time.LocalDate;

public record ClassDetails(
		int id, 
		int courseId,
		String courseName,
		LocalDate startDate,
		int fees,
		int months,
		long students) {

}
