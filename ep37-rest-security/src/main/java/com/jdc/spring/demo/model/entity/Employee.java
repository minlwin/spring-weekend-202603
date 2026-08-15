package com.jdc.spring.demo.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jdc.spring.demo.model.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Employee extends AbstractEntity {

	@Id
	private int id;
	
	@MapsId
	@OneToOne(optional = false)
	private Account account;
	
	@Column(nullable = false)
	private String phone;
	
	private LocalDateTime activatedAt;
	
	private LocalDate retiredAt;
}
