package com.jdc.spring.demo.model.entity;

import java.time.LocalDate;
import java.util.UUID;

import com.jdc.spring.demo.model.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class CandidateCertificates extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@ManyToOne(optional = false)
	private Candidate candidate;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private LocalDate issueDate;
	
	private LocalDate expireDate;

	@Column(nullable = false)
	private String attachment;
}
