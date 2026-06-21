package com.jdc.shop.model.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Employee extends Member{

	@Column(nullable = false)
	private String phone;
	
	private LocalDate retiredAt;
}
