package com.jdc.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@SequenceGenerator(sequenceName = "emp_sequance", initialValue = 1, allocationSize = 1)
public class Employee extends AuditableEntity{

	@Id
	@GeneratedValue(generator = "emp_sequance")
	private int id;
	
	@Column(unique = true, nullable = false)
	private String name;
	
	private Contact contact;
	
	
}
