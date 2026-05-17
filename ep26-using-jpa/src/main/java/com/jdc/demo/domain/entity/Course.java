package com.jdc.demo.domain.entity;

import com.jdc.demo.domain.constants.CourseLevel;
import com.jdc.demo.domain.entity.embeddable.AuditInfo;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Enumerated(EnumType.STRING)
	private CourseLevel level;
	private int hours;
	private String description;
	
	@Embedded
	private AuditInfo auditInfo;
}
