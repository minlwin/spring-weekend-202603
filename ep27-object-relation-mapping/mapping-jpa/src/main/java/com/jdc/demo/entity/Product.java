package com.jdc.demo.entity;

import java.util.Map;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "product", indexes = {
		@Index(columnList = "name")
})
@SequenceGenerator(name = "product_seq")
public class Product {

	@Id
	@GeneratedValue(generator = "product_seq")
	private int id;
	@Column(nullable = false, length = 40)
	private String name;
	@Column(name = "unit_price", nullable = false)
	private int unitPrice;
	
	@ManyToOne(optional = false)
	private Category category;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ElementCollection
	@CollectionTable(
		name = "product_tags", 
		joinColumns = @JoinColumn(name="product_id")
	)
	private Set<String> tags;
	
	@ElementCollection
	@MapKeyColumn(name = "props_key")
	@CollectionTable(
			name = "product_props",
			joinColumns = @JoinColumn(name="product_id")
	)
	private Map<String, String> properties;
	
	public enum Status {
		Available, OutOfStock
	}
	
}
