package com.jdc.spring.demo.api.management.input;

import java.time.LocalDate;

public record CustomerSearch(
		LocalDate dateFrom, 
		LocalDate dateTo,
		String keyword) {

}
