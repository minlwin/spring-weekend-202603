package com.jdc.demo.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Invoice {

	@EmbeddedId
	private InvoiceId id;
	
	private int amount;
}
