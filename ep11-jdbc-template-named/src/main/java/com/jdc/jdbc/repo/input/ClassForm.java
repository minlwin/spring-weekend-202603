package com.jdc.jdbc.repo.input;

import java.time.LocalDate;

public record ClassForm(
		Integer courseId,
		LocalDate startDate,
		Integer months) {

}
