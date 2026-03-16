package com.jdc.jdbc.repo.output;

import java.time.LocalDate;

public record ClassItem(
		int id, 
		int courseId,
		String courseName,
		LocalDate startDate,
		int fees,
		int months) {

}
