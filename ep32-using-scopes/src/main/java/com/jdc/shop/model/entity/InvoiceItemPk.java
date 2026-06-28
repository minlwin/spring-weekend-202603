package com.jdc.shop.model.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class InvoiceItemPk {

	@Column(name = "invoice_id")
	private UUID invoiceId;
	
	@Column(name = "seq_number")
	private int seqNumber;
}
