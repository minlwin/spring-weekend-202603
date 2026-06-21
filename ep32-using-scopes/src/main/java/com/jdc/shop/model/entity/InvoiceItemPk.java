package com.jdc.shop.model.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class InvoiceItemPk {

	@Column(name = "inoivce_id", insertable = false, updatable = false)
	private UUID invoiceId;

	@Column(nullable = false)
	private int seqNumber;
}
