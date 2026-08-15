package com.jdc.spring.demo.api.management.output;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jdc.spring.demo.model.entity.Employee;

public record EmployeeDetails(
		int id,
		String name,
		String email,
		String phone,
		LocalDateTime activatedAt,
		LocalDate retiredAt,
		String createdBy,
		String modifiedBy,
		LocalDateTime createdAt,
		LocalDateTime modifiedAt) {

	public static EmployeeDetails from(Employee entity) {
		return new EmployeeDetails(
				entity.getId(), 
				entity.getAccount().getName(), 
				entity.getAccount().getEmail(), 
				entity.getPhone(), 
				entity.getActivatedAt(), 
				entity.getRetiredAt(), 
				entity.getCreatedBy(), 
				entity.getModifiedBy(), 
				entity.getCreatedAt(), 
				entity.getModifiedAt());
	}
}
