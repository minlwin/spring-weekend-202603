package com.jdc.jdbc.repo.input;

import java.time.LocalDate;

public record RegistrationSearch(
		String keyword,
		LocalDate from,
		LocalDate to) {

}
