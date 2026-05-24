package com.jdc.demo.entity;

import com.jdc.demo.entity.pk.CustomerContactId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class CustomerContact {

	@EmbeddedId
	private CustomerContactId id;
	
	@ManyToOne(optional = false)
	private Customer customer;
	
	private String phone;
	private String email;
	private String address;
}
