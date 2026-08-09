package com.jdc.spring.demo.api.management.output;

import java.time.LocalDateTime;

public record EmployeeDetails(
		int id,
		String name,
		String email,
		String phone,
		LocalDateTime activatedAt,
		LocalDateTime retiredAt,
		String createdBy,
		String modifiedBy,
		LocalDateTime createdAt,
		LocalDateTime modifiedAt) {

}
