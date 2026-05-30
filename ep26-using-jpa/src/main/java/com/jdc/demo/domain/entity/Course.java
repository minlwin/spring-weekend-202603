package com.jdc.demo.domain.entity;

import com.jdc.demo.domain.AbstractEntity;
import com.jdc.demo.domain.constants.CourseLevel;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Course extends AbstractEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	
	// LEVEL -> Basic, Intermediate, Advance
	@Enumerated(EnumType.STRING)
	private CourseLevel level;
	private int hours;
	private String description;
}
