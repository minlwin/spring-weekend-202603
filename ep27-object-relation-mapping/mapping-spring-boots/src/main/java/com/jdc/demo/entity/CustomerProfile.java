package com.jdc.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class CustomerProfile {

	@Id
	private int id;
	
	@MapsId
	@OneToOne(optional = false)
	private Customer customer;
	
	private String greeting;
	private String backgroundPhoto;
	private String profilePhoto;
}
