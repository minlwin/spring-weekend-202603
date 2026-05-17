package com.jdc.demo.entity;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Project {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	private LocalDate startDate;
	
	@ElementCollection
	private List<String> tags;
	
	@ElementCollection
	private Map<DayOfWeek, Integer> workingHours;
	
	@ElementCollection
	private List<Contact> contacts;

}
