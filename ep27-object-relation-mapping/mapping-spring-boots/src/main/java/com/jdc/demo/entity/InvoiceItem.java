package com.jdc.demo.entity;

import com.jdc.demo.entity.pk.InvoiceItemId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class InvoiceItem {

	@EmbeddedId
	private InvoiceItemId id;
	
	@ManyToOne(optional = false)
	private Invoice invoice;

	@ManyToOne(optional = false)
	private Product product;
	
	private int unitPrice;
	private int quantity;
	
	private String remark;
}
