package com.jdc.demo.entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(indexes = {
		@Index(columnList = "name")
})
public class Product {

	@Id
	@GeneratedValue
	private int id;
	@Column(nullable = false, length = 40)
	private String name;
	@Column(nullable = false)
	private int unitPrice;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Enumerated(EnumType.STRING)
	private Status status;
	
	private Set<String> tags;	

	@Lob
	private List<Property> properties;
	
	public enum Status {
		Available, OutOfStock
	}

}
