package com.jdc.demo.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Member {

	@Id
	private int id;
	
	@MapsId
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
	private Account account;
	
	@Column(nullable = false)
	private String name;
	
	private String profileImage;
	private String phone;
	private String address;
}
