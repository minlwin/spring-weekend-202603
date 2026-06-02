package com.jdc.demo.domain.input;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RegistrationSearch {
	private LocalDate from;
	private LocalDate to;
	private String keyword;
}
