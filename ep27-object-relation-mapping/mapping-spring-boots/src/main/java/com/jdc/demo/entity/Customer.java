package com.jdc.demo.entity;

import java.util.List;
import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	
	@OneToOne(mappedBy = "customer")
	private CustomerProfile profile;
	
	@MapKeyColumn(name = "seq_number")
	@OneToMany(mappedBy = "customer")
	private Map<Integer, CustomerContact> contacts;
	
	@ManyToMany
	private List<Topic> subscription;
}
