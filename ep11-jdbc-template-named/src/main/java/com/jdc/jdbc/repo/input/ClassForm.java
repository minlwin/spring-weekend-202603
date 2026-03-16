package com.jdc.jdbc.repo.input;

import java.time.LocalDate;

public record ClassForm(
		int courseId,
		LocalDate startFrom,
		LocalDate startTo) {

}
