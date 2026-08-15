package com.jdc.spring.demo.api.management.output;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jdc.spring.demo.model.entity.Customer;

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

	public static CustomerDetails from(Customer entity) {
		return new CustomerDetails(
				entity.getId(), 
				entity.getAccount().getName(), 
				entity.getAccount().getEmail(), 
				entity.getPhone(), 
				entity.getRegisterdAt(), 
				entity.getDob(), 
				entity.getCreatedBy(), 
				entity.getModifiedBy(), 
				entity.getCreatedAt(), 
				entity.getModifiedAt());
		
	}
}
