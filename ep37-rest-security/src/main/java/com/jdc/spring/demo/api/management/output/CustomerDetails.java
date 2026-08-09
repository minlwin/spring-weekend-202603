package com.jdc.spring.demo.api.management.output;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CustomerDetails(		
		int id,
		String name,
		String email,
		String phone,
		LocalDateTime registeredAt,
		LocalDate dob,
		String createdBy,
		String modifiedBy,
		LocalDateTime createdAt,
		LocalDateTime modifiedAt) {

}
