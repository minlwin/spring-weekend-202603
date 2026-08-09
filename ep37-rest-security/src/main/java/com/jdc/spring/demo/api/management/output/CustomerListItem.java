package com.jdc.spring.demo.api.management.output;

import java.time.LocalDateTime;

public record CustomerListItem(
		int id,
		String name,
		String email,
		String phone,
		LocalDateTime registeredAt) {

}
