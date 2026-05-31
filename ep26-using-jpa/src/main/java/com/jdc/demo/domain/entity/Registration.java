package com.jdc.demo.domain.entity;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

import com.jdc.demo.domain.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Registration extends AbstractEntity{

	@Id
	@GeneratedValue
	private UUID id;
	
	@ManyToOne(optional = false)
	private Classes intake;
	
	@ManyToOne(optional = false)
	private Student student;
	
	@Column(nullable = false)
	private LocalDate registDate;
	
	@Column(nullable = false)
	private BigInteger paid;
}
