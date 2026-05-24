package com.jdc.demo.entity;

import java.util.List;

import com.jdc.demo.entity.pk.InvoiceId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Invoice {

	@EmbeddedId
	private InvoiceId id;
	
	@ManyToOne(optional = false)
	private Customer customer;
	
	@OneToMany(mappedBy = "invoice")
	private List<InvoiceItem> items;
	
}
