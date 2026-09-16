package com.jdc.spring.demo.model.entity;

import java.time.LocalDate;
import java.util.List;

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
public class PartnerCompany extends AbstractEntity{

	@Id
	private int id;
	
	@MapsId
	@OneToOne(optional = false)
	private Partner partner;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private LocalDate foundAt;
	@Column(nullable = false)
	private List<String> categories;
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private String description;

	private String logoImage;
	private String coverImage;
	
}
