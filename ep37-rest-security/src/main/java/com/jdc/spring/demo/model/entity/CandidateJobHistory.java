package com.jdc.spring.demo.model.entity;

import java.time.LocalDate;
import java.util.UUID;

import com.jdc.spring.demo.model.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class CandidateJobHistory extends AbstractEntity{

	@GeneratedValue
	private UUID id;
	
	@ManyToOne(optional = false)
	private Candidate candidate;

	@Column(nullable = false)
	private String jobTitle;

	@Column(nullable = false)
	private String jobCategory;

	@Column(nullable = false)
	private String company;
	
	@Column(nullable = false)
	private LocalDate startAt;

	private LocalDate endAt;

}
