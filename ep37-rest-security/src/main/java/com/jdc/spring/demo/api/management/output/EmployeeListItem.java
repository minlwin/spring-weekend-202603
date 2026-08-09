package com.jdc.spring.demo.api.management.output;

public record EmployeeListItem(
		int id,
		String name,
		String email,
		String phone,
		boolean activated) {

}
