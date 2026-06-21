package com.jdc.shop.model.entity;

import java.util.List;
import java.util.UUID;

import com.jdc.shop.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity{

	@Id
	@GeneratedValue
	private UUID id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private int price;
	
	@ManyToMany
	private List<Category> category;
}
