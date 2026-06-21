package com.jdc.shop.model.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.jdc.shop.model.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Invoice extends BaseEntity {

	@Id
	@GeneratedValue
	private UUID id;
	
	@ManyToOne
	private Customer customer;
	
	@Column(nullable = false)
	private LocalDateTime invoiceAt;
	
	@Column(nullable = false)
	private Status status;
	
	@OneToMany(
		mappedBy = "invoice", 
		cascade = {CascadeType.PERSIST, CascadeType.MERGE},
		orphanRemoval = true
	)
	private List<InvoiceItem> items;
	
	public enum Status {
		Invoiced, Delivered, Canceled, Finished
	}
}
