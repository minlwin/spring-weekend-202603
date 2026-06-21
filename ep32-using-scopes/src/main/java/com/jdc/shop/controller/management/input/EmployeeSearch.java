package com.jdc.shop.controller.management.input;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EmployeeSearch {

	private LocalDate from;
	private LocalDate to;
	private String keyword;

}
