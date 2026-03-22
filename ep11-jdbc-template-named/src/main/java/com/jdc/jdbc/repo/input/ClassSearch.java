package com.jdc.jdbc.repo.input;

import java.time.LocalDate;

public record ClassSearch(
		String keyword,
		LocalDate dateFrom,
		LocalDate dateTo) {

}
