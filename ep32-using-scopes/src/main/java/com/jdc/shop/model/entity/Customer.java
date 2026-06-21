package com.jdc.shop.model.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Customer extends Member {

	private String phone;
	private String address;
	
	@OneToMany(mappedBy = "customer")
	private List<Invoice> invoices;
}
