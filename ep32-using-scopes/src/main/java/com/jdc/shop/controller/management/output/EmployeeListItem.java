package com.jdc.shop.controller.management.output;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class EmployeeListItem {

	private UUID id;
	private String name;
	private String phone;
	private String email;
	private LocalDateTime entryAt;
	private LocalDate retiredAt;
}
