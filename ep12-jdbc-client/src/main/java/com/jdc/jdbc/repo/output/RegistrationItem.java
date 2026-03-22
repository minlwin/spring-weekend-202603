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

	public String getCode() {
		return "%03d%06d".formatted(classId, studentId);
	}
}
