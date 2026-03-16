package com.jdc.jdbc.repo.output;

import java.time.LocalDate;

public record RegistrationItem(
		int classId,
		int courseId,
		String courseName,
		int fees,
		LocalDate startAt,
		int studentId,
		String studentName,
		String phone,
		String email) {

}
