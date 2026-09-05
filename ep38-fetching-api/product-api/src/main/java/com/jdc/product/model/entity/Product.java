package com.jdc.product.model.entity;

import java.time.LocalDateTime;

import com.jdc.product.model.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Data
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String category;
	@Column(nullable = false)
	private int unitPrice;
	@Column(nullable = false)
	private Status status;
	private String description;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
	
	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
	}
	
	@PreUpdate
	public void preUpdate() {
		modifiedAt = LocalDateTime.now();
	}
}
