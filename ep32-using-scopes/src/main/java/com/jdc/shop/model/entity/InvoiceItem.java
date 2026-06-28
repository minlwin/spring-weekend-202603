package com.jdc.shop.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class InvoiceItem {

	@EmbeddedId
	private InvoiceItemPk id;
	
	@ManyToOne(optional = false)
	private Product product;
	
	@Column(nullable = false)
	private int unitPrice;

	@Column(nullable = false)
	private int quantity;
	
	@ManyToOne(optional = false)
	@JoinColumn(insertable = false, updatable = false)
	private Invoice invoice;
}
