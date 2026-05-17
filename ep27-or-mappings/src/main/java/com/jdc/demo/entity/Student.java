package com.jdc.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SecondaryTable;
import lombok.Data;

@Data
@Entity
@SecondaryTable(name = "grade")
@SecondaryTable(name = "gurdian")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	private String name;
	
	@Column(table = "grade", name = "name")
	private String grade;
	
	@Column(table = "gurdian", name = "name")
	private String gurdianName;
	@Column(table = "gurdian", name = "phone")
	private String gurdianPhone;
}
