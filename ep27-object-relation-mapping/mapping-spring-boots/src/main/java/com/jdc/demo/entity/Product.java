package com.jdc.demo.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
	
	@ManyToOne(optional = false)
	private Category category;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ElementCollection
	private Set<String> tags;	

	@ElementCollection
	private Map<String, String> properties;
	
	@ManyToMany
	@JoinTable(name = "invoice_item", joinColumns = {
			@JoinColumn(name="id", referencedColumnName = "product_id", insertable = false, updatable = false)
	})
	private List<Invoice> invoices;
	
	public enum Status {
		Available, OutOfStock
	}

}
